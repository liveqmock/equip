package xi.ym.equip.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import xi.ym.equip.pageModel.Json;
import xi.ym.equip.pageModel.Role;
import xi.ym.equip.service.RoleServiceI;

import com.opensymphony.xwork2.ModelDriven;

/**
 * <pre>
 * <p>Description:</p>
 * @version 1.0
 * @date 2015年2月17日 
 * @author LRB
 * </pre>
 */
@Namespace("/")
@Action(value = "roleAction", interceptorRefs = { @InterceptorRef("mydefaultstack") })
public class RoleAction extends BaseAction implements ModelDriven<Role> {
	private static final long serialVersionUID = -4649695667410735730L;
	private Role role = new Role();
	private Integer userId;
	private RoleServiceI roleService;

	@Autowired
	public void setRoleService(RoleServiceI roleService) {
		this.roleService = roleService;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public Role getModel() {
		return role;
	}

	public void search() {
		super.writeJson(roleService.search(role));
	}

	public void roleadd() {
		Json j = new Json();
		try {
			Role u = roleService.save(role);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		super.writeJson(j);
	}

	public void remove() {
		roleService.remove(role.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}

	public void grantmenu() {
		Json j = new Json();
		// Integer id = role.getId();
		// System.out.println(id);
		// System.out.println(menuIds);
		try {
			roleService.addMenu(role, role.getMenuIds());
			j.setSuccess(true);
			j.setMsg("添加菜单成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		super.writeJson(j);
	}

	public void getAllTreeNode() {
		super.writeJson(roleService.getAllTreeNode());
	}

	public void getAllTreeNodeChecked() {
		// System.out.println("userId=" + userId);
		super.writeJson(roleService.getAllTreeNodeIncludeChecked(userId));
	}
}
