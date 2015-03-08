package xi.ym.equip.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import xi.ym.equip.pageModel.User;
import xi.ym.equip.service.MenuServiceI;

@Action("ztreeAction")
@Namespace("/")
public class ZtreeAction extends BaseAction {
	private static final long serialVersionUID = 6347165280391218179L;
	private MenuServiceI menuService;

	@Autowired
	public void setMenuService(MenuServiceI menuService) {
		this.menuService = menuService;
	}

	public void getAllTreeNodeWithRight() {
		User user = (User) session.get("user");
		super.writeJson(menuService.getAllTreeNodeWithRightZtree(user));
	}
}
