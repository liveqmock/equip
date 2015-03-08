package xi.ym.equip.action;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.SessionAware;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * <pre>
 * 基类action
 * 主要提供json 的输出
 * @author LRB
 * </pre>
 */
@ParentPackage("basePackage")   
@Namespace("/")
public class BaseAction extends ActionSupport implements SessionAware {
	/**
	 * Logger for this class   
	 */      
	private static final Logger logger = Logger.getLogger(BaseAction.class);

	private static final long serialVersionUID = 749767758324949318L;
	protected Map<String, Object> session = null;

	/**
	 * 输出json
	 * 
	 * @param object
	 *            要输出的类型，可以是list object etc.
	 */
	public void writeJson(Object object) {
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

	@Override
	public void setSession(Map<String, Object> session) {
		//
		this.session = session;
	}
}
