package xi.ym.equip.action;

import org.apache.log4j.Logger;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import xi.ym.equip.pageModel.Json;
import xi.ym.equip.pageModel.User;
import xi.ym.equip.service.UserServiceI;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @name 用户Action
 * @description 主要完成用户这一实体的相关操作
 * @author LRB
 * 
 */
@Namespace("/")
@Action(value = "userAction", interceptorRefs = { @InterceptorRef("mydefaultstack") })
public class UserAction extends BaseAction implements ModelDriven<User> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserAction.class);

	private static final long serialVersionUID = -7670670684832044533L;
	private User user = new User();
	private String roleIds;

	@Override
	public User getModel() {
		return user;
	}

	private UserServiceI userService;

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public void useradd() {
		Json j = new Json();
		try {
			User u = userService.save(user);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		super.writeJson(j);

	}

	public void userupdate() {
		Json j = new Json();
		try {
			logger.info(user.getId());
			User u = userService.update(user);
			j.setSuccess(true);
			j.setMsg("修改成功！");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		super.writeJson(j);

	}

	public void login() {
		User u = userService.login(user);
		session.put("user", u);
		Json j = new Json();
		if (u != null) {
			j.setSuccess(true);
			j.setMsg("登陆成功！");
		} else {
			j.setMsg("登录失败，用户名或密码错误！");
		}

		super.writeJson(j);
	}

	public void logout() {

		session.clear();
		// 重定向到主页
		try {
			ServletActionContext.getResponse().sendRedirect("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void usersearch() {
		super.writeJson(userService.usersearch(user));
	}

	public void remove() {
		userService.remove(user.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}

	public void grantrole() {
		Json j = new Json();
		Integer id = user.getId();
		logger.info(id);
		logger.info(roleIds);
		try {
			userService.addRole(user, roleIds);
			j.setSuccess(true);
			j.setMsg("添加角色成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		super.writeJson(j);
	}

	public void editpassword() {
		Json j = new Json();
		try {
			User sessionUser = (User) session.get("user");
			// 把id传给user,
			user.setId(sessionUser.getId());
			logger.info(user.getUserPwd());
			userService.updatePassword(user);
			j.setSuccess(true);
			j.setMsg("修改成功，新密码是：" + user.getUserPwd());
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
}
