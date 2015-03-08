package xi.ym.equip.service;

import java.util.List;
import java.util.Set;

import xi.ym.equip.pageModel.DataGrid;
import xi.ym.equip.pageModel.Menu;
import xi.ym.equip.pageModel.User;
import xi.ym.equip.pageModel.ZtreeMenu;

public interface MenuServiceI {
	/**
	 * 获取叶子节点
	 * 
	 * @param integer
	 * @return
	 */
	public List<Menu> getTreeNode(Integer integer);

	/**
	 * 获取所有节点
	 * 
	 * @param user
	 * 
	 * @param integer
	 * @return
	 */
	public List<Menu> getAllTreeNodeWithRight(User user);

	public List<Menu> getAllTreeNode();

	Set<Menu> getAllTreeNodeChecked(Integer roleId);

	public DataGrid search(Menu menu);

	public List<ZtreeMenu> getAllTreeNodeWithRightZtree(User user); 

}
