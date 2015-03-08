package xi.ym.equip.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import xi.ym.equip.dao.MenuDaoI;
import xi.ym.equip.dao.RoleDaoI;
import xi.ym.equip.dao.UserDaoI;
import xi.ym.equip.domain.Pmenu;
import xi.ym.equip.domain.Prole;
import xi.ym.equip.domain.Puser;
import xi.ym.equip.pageModel.DataGrid;
import xi.ym.equip.pageModel.Menu;
import xi.ym.equip.pageModel.User;
import xi.ym.equip.pageModel.ZtreeMenu;
import xi.ym.equip.service.MenuServiceI;

@Service("menuService")
public class MenuServiceImpl implements MenuServiceI {
	@Resource
	private MenuDaoI menuDao;
	@Resource
	private UserDaoI userDao;
	@Resource
	private RoleDaoI roleDao;

	@Override
	public List<Menu> getTreeNode(Integer id) {
		List<Menu> nl = new ArrayList<Menu>();
		String hql = null;
		Map<String, Object> params = new HashMap<String, Object>();
		if (id == null || id.equals("")) {
			// 查询所有根节点
			hql = "FROM Pmenu p WHERE 1=1 AND p.type=0 AND p.pmenu IS NULL";
		} else {
			// 异步加载当前id下的子节点
			hql = "FROM Pmenu p WHERE 1=1 AND p.type=0 AND p.pmenu.id = :id ";
			params.put("id", id);
		}
		List<Pmenu> l = menuDao.find(hql, params);
		if (l != null && l.size() > 0) {
			for (Pmenu t : l) {
				Menu m = new Menu();
				BeanUtils.copyProperties(t, m);
				Set<Pmenu> set = t.getPmenus();
				if (set != null && !set.isEmpty()) {
					m.setState("closed");// 节点以文件夹的形式体现
				} else {
					m.setState("open");// 节点以文件的形式体现
				}
				nl.add(m);
			}
		}
		return nl;
	}

	@Override
	public List<Menu> getAllTreeNodeWithRight(User user) {
		List<Menu> nl = new ArrayList<Menu>();
		List<Pmenu> l = new ArrayList<Pmenu>();

		if (user == null) {
			String hql = "FROM Pmenu p WHERE 1=1 AND p.type=0 ";
			l = menuDao.find(hql);
			exchange(nl, l);
			return nl;
		} else {
			//
			Integer uid = user.getId();
			Puser puser = userDao.get(Puser.class, uid);
			/*
			 * 回显权限树的时候有点难度，待考虑
			 */
			Set<Prole> roles = puser.getRoles();
			for (Prole prole : roles) {
				Set<Pmenu> menus = prole.getMenus();
				for (Pmenu pmenu : menus) {
					// 菜单的父菜单不为空，把顶级菜单去除
					if (pmenu.getPmenu() != null) {
						// 拿到菜单的jsp页面
						if (pmenu.getType() == 0) {
							l.add(pmenu);
							// 父级菜单只添加一次。
							if (!l.contains(pmenu.getPmenu()))
								l.add(pmenu.getPmenu());
						}
					}
				}
			}
			//

			// 转换
			exchange(nl, l);
			return nl;
		}

	}

	private void exchange(Collection<Menu> nall, Collection<Pmenu> all) {
		if (all != null && all.size() > 0) {
			for (Pmenu p : all) {
				Menu m = new Menu();
				BeanUtils.copyProperties(p, m);
				Map<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("url", p.getUrl());
				m.setAttributes(attributes);

				Pmenu tm = p.getPmenu();// 获得当前节点的父节点对象
				if (tm != null) {
					m.setPid(tm.getId());
				} else {
					m.setState("closed");
				}
				nall.add(m);
			}
		}
	}

	@Override
	public DataGrid search(Menu menu) {
		DataGrid dg = new DataGrid();
		StringBuffer hql = new StringBuffer("FROM Pmenu p ");
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(menu, hql, params);

		StringBuffer totalHql = new StringBuffer("SELECT COUNT(*) " + hql);
		hql = addOrder(menu, hql);

		List<Pmenu> l = menuDao.find(hql.toString(), params, menu.getPage(), menu.getRows());
		List<Menu> nl = new ArrayList<Menu>();
		changeModel(l, nl);
		dg.setTotal(menuDao.count(totalHql.toString(), params));
		dg.setRows(nl);
		return dg;
	}

	/**
	 * 添加排序条件
	 * 
	 * @param eqRecord
	 *            VO
	 * @param hql
	 *            传入hql
	 * @return 拼装之后的hql
	 */
	private StringBuffer addOrder(Menu menu, StringBuffer hql) {
		if (menu.getSort() != null) {
			hql.append(" ORDER BY " + menu.getSort() + " " + menu.getOrder());
		}
		return hql;
	}

	/**
	 * 添加过滤条件
	 * 
	 * @param eqRecord
	 *            VO
	 * @param hql
	 *            hql
	 * @param params
	 *            hql
	 * @return 拼装之后 hql
	 */
	private StringBuffer addWhere(Menu menu, StringBuffer hql, Map<String, Object> params) {
		if (menu.getText() != null && !menu.getText().trim().equals("")) {
			hql.append(" WHERE p.text LIKE :text");
			params.put("text", "%%" + menu.getText().trim() + "%%");
		}
		return hql;
	}

	/**
	 * 转换PO<--->VO
	 * 
	 * @param l
	 * @param nl
	 */
	private void changeModel(List<Pmenu> l, List<Menu> nl) {
		if (l != null && l.size() > 0) {
			for (Pmenu p : l) {
				Menu m = new Menu();
				BeanUtils.copyProperties(p, m);
				Pmenu tm = p.getPmenu();// 获得当前节点的父节点对象
				if (tm != null) {
					m.setPid(tm.getId());
				} else {
					m.setState("closed");
				}
				nl.add(m);
			}
		}
	}

	@Override
	public List<Menu> getAllTreeNode() {
		List<Menu> nl = new ArrayList<Menu>();
		String hql = "FROM Pmenu p";
		List<Pmenu> l = menuDao.find(hql);
		exchange(nl, l);
		return nl;
	}

	public Set<Menu> getAllTreeNodeChecked(Integer roleId) {

		// 获得所有的菜单页面
		String hql = "FROM Pmenu p WHERE 1=1 ";
		Set<Pmenu> all = new HashSet<Pmenu>(menuDao.find(hql));
		Set<Menu> nall = new HashSet<Menu>();
		exchange(nall, all);
		addChecked(nall, "");

		// 获得角色
		Prole prole = roleDao.get(Prole.class, roleId);
		Set<Pmenu> l = prole.getMenus();
		Set<Menu> nl = new HashSet<Menu>();
		exchange(nl, l);
		// 遍历当前角色的menus
		for (Menu menu : nl) {
			// menu中去除顶级菜单
			if (menu.getPid() != null)
				for (Menu m : nall) {
					if (m.getId().intValue() == menu.getId().intValue() && //
							menu.getType() == 1) {
						m.setChecked("true");
					}
				}
		}

		return nall;
	}

	private void addChecked(Set<Menu> nl, String checked) {
		if (nl != null && nl.size() > 0) {
			for (Menu m : nl) {
				m.setChecked(checked);
			}
		}
	}

	@Override
	public List<ZtreeMenu> getAllTreeNodeWithRightZtree(User user) {
		List<ZtreeMenu> nl = new ArrayList<ZtreeMenu>();
		List<Pmenu> l = new ArrayList<Pmenu>();

		if (user == null) {
			String hql = "FROM Pmenu p WHERE 1=1 AND p.type=0 ";
			l = menuDao.find(hql);
			exchangeZtree(nl, l);
			return nl;
		} else {
			//
			Integer uid = user.getId();
			Puser puser = userDao.get(Puser.class, uid);
			/*
			 * 回显权限树的时候有点难度，待考虑
			 */
			Set<Prole> roles = puser.getRoles();
			for (Prole prole : roles) {
				Set<Pmenu> menus = prole.getMenus();
				for (Pmenu pmenu : menus) {
					// 菜单的父菜单不为空，把顶级菜单去除
					if (pmenu.getPmenu() != null) {
						// 拿到菜单的jsp页面
						if (pmenu.getType() == 0) {
							l.add(pmenu);
							// 父级菜单只添加一次。
							if (!l.contains(pmenu.getPmenu()))
								l.add(pmenu.getPmenu());
						}
					}
				}
			}
			//

			// 转换
			exchangeZtree(nl, l);
			return nl;
		}
	}

	private void exchangeZtree(List<ZtreeMenu> nall, List<Pmenu> all) {
		if (all != null && all.size() > 0) {
			for (Pmenu p : all) {
				ZtreeMenu m = new ZtreeMenu();
				m.setId(p.getId());
				m.setOurl(p.getUrl());
				m.setName(p.getText());
				m.setIconCls(p.getIconCls());
				m.setIconSkin(p.getIconSkin());
				m.setType(p.getType());
				Pmenu parentMenu = p.getPmenu();// 获得当前节点的父节点对象
				if (parentMenu != null) {
					m.setPid(parentMenu.getId());
				} else {
					m.setOpen("false");
					m.setIconSkin(p.getIconSkin());
				}
				nall.add(m);
			}
		}
	}

} 
