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
import xi.ym.equip.pageModel.Role;
import xi.ym.equip.service.RoleServiceI;

@Service("roleService")
public class RoleServiceImpl implements RoleServiceI {
	@Resource
	private RoleDaoI roleDao;
	@Resource
	private MenuDaoI menuDao;
	@Resource
	private UserDaoI userDao;

	@Override
	public DataGrid search(Role role) {
		DataGrid dg = new DataGrid();
		StringBuffer hql = new StringBuffer("FROM Prole p ");
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(role, hql, params);

		StringBuffer totalHql = new StringBuffer("SELECT COUNT(*) " + hql);
		hql = addOrder(role, hql);

		List<Prole> l = roleDao.find(hql.toString(), params, role.getPage(), role.getRows());
		List<Role> nl = new ArrayList<Role>();
		changeModel(l, nl);
		dg.setTotal(roleDao.count(totalHql.toString(), params));
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
	private StringBuffer addOrder(Role role, StringBuffer hql) {
		if (role.getSort() != null) {
			hql.append(" ORDER BY " + role.getSort() + " " + role.getOrder());
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
	private StringBuffer addWhere(Role role, StringBuffer hql, Map<String, Object> params) {
		if (role.getRoleName() != null && !role.getRoleName().trim().equals("")) {
			hql.append(" WHERE p.roleName LIKE :roleName");
			params.put("roleName", "%%" + role.getRoleName().trim() + "%%");
		}
		return hql;
	}

	/**
	 * 转换PO<--->VO
	 * 
	 * @param l
	 * @param nl
	 */
	private void changeModel(Collection<Prole> l, Collection<Role> nl) {
		if (l != null && l.size() > 0) {
			for (Prole p : l) {
				Role r = new Role();
				BeanUtils.copyProperties(p, r);

				nl.add(r);
			}
		}
	}

	private void addChecked(Set<Role> nl, String checked) {
		if (nl != null && nl.size() > 0) {
			for (Role r : nl) {
				r.setChecked(checked);
			}
		}
	}

	@Override
	public Role save(Role role) {
		Prole p = new Prole();
		BeanUtils.copyProperties(role, p);

		roleDao.save(p);
		return role;
	}

	@Override
	public void remove(String ids) {
		String[] nids = ids.split(",");
		StringBuffer hql = new StringBuffer("DELETE Prole p WHERE p.id IN (");
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql.append(",");
			}
			hql.append("'" + nids[i] + "'");
		}
		hql.append(")");
		roleDao.executeHql(hql.toString());
	}

	@Override
	public void addMenu(Role role, String menuIds) {
		//
		String[] nids = menuIds.split(",");
		HashSet<Pmenu> ms = new HashSet<Pmenu>();
		for (String id : nids) {
			Pmenu p = menuDao.get(Pmenu.class, Integer.parseInt(id));
			ms.add(p);
		}

		Integer rid = role.getId();
		Prole prole = roleDao.get(Prole.class, rid);
		// 先删除，后更新
		prole.getMenus().clear();

		prole.setMenus(ms);
		// 更新操作

		roleDao.update(prole);
	}

	@Override
	public List<Role> getAllTreeNode() {
		//
		List<Role> nl = new ArrayList<Role>();
		String hql = "FROM Prole p";
		List<Prole> l = roleDao.find(hql);
		exchange(nl, l);
		return nl;
	}

	private void exchange(Collection<Role> nl, Collection<Prole> l) {
		if (l != null && l.size() > 0) {
			for (Prole p : l) {
				Role r = new Role();
				BeanUtils.copyProperties(p, r);
				r.setText(p.getRoleName());
				nl.add(r);
			}
		}
	}

	@Override
	public Set<Role> getAllTreeNodeIncludeChecked(Integer userId) {

		// 获得所有的角色
		String hql = "FROM Prole p WHERE 1=1 ";
		Set<Prole> all = new HashSet<Prole>(roleDao.find(hql));
		Set<Role> nall = new HashSet<Role>();
		exchange(nall, all);
		addChecked(nall, "");

		// 所给用户拥有的角色
		Puser puser = userDao.get(Puser.class, userId);
		Set<Prole> l = puser.getRoles();
		Set<Role> nl = new HashSet<Role>();
		exchange(nl, l);

		for (Role role : nl) {
			for (Role r : nall) {
				if (r.getId().intValue() == role.getId().intValue()) {
					r.setChecked("true");
				}
			}
		}

		return nall;
	}

}
