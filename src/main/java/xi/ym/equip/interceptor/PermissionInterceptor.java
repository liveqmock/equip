package xi.ym.equip.interceptor;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import xi.ym.equip.domain.Pmenu;
import xi.ym.equip.domain.Prole;
import xi.ym.equip.pageModel.Json;
import xi.ym.equip.pageModel.User;
import xi.ym.equip.service.UserServiceI;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * <pre>
 * <p>Description:权限拦截器</p>
 * @version 1.0
 * @date 2015年2月20日 
 * @author LRB
 * </pre>
 */
@SuppressWarnings("serial")
public class PermissionInterceptor extends AbstractInterceptor {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PermissionInterceptor.class);
	@Resource(name = "userService")
	private UserServiceI userService;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		if (user == null) {
			// 写json
			Json j = new Json();
			try {
				j.setSuccess(false);
				j.setMsg("对不起，请先登录");
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
			return invocation.invoke();
		}
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String method = invocation.getProxy().getMethod();
		if (null == namespace || "".equals(namespace)) {
			namespace = "/";
		}
		if (!namespace.endsWith("/")) {
			namespace += "/";
		}
		String url = namespace + actionName + "!" + method + ".action";
		logger.info("权限拦截器执行了:" + url);
		Set<Prole> proles = userService.getRoles(user);

		// 获取当前登录用户权限组id
		if (proles != null && proles.size() > 0) {
			for (Prole prole : proles) {
				Set<Pmenu> menus = prole.getMenus();
				if (menus != null && menus.size() > 0) {
					for (Pmenu pmenu : menus) {
						if (url.equals(pmenu.getUrl())) {
							return invocation.invoke();
						}
					}
				}
			}
		}
		// 写json
		Json j = new Json();
		try {
			j.setSuccess(false);
			j.setMsg("对不起，你没有权限！！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		writeJson(j);
		return null;
	}

	private void writeJson(Object object) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			logger.info(json); 
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
