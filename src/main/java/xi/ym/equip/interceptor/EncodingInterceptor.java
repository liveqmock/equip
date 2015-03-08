package xi.ym.equip.interceptor;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * <pre>
 * <p>Description:</p>
 * @version 1.0
 * @date 2015年2月17日 
 * @author LRB
 * </pre>
 */ 
public class EncodingInterceptor extends AbstractInterceptor {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EncodingInterceptor.class);

	private static final long serialVersionUID = -7468031829833214755L;

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		// ActionContext actionContext = actionInvocation.getInvocationContext();
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
		logger.info("字符编码拦截器"); 
		return actionInvocation.invoke();
	}

}
