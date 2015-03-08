package xi.ym.equip.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import xi.ym.equip.pageModel.Menu;
import xi.ym.equip.pageModel.User;
import xi.ym.equip.service.MenuServiceI;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @name 菜单Action
 * @description 主要完成菜单这一实体的相关操作
 * @author LRB
 * 
 */
@Namespace("/")
@Action(value = "menuAction", interceptorRefs = { @InterceptorRef("mydefaultstack") })
public class MenuAction extends BaseAction implements ModelDriven<Menu> {
	private static final long serialVersionUID = -2586606527093078431L;

	private Menu menu = new Menu();
	private Integer roleId;

	private MenuServiceI menuService;

	public MenuServiceI getMenuService() {
		return menuService;
	}

	@Autowired
	public void setMenuService(MenuServiceI menuService) {
		this.menuService = menuService;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public Menu getModel() {
		return menu;
	}

	/**
	 * 异步获取树节点
	 */
	public void getTreeNode() {
		super.writeJson(menuService.getTreeNode(menu.getId()));
	}

	/**
	 * 获得所有节点
	 */
	public void getAllTreeNode() {
		super.writeJson(menuService.getAllTreeNode());
	}

	public void getAllTreeNodeWithRight() {
		User user = (User) session.get("user");
		super.writeJson(menuService.getAllTreeNodeWithRight(user));
	}

	public void search() {
		super.writeJson(menuService.search(menu));
	}

	public void getAllTreeNodeChecked() {
		super.writeJson(menuService.getAllTreeNodeChecked(roleId));
	}
}
