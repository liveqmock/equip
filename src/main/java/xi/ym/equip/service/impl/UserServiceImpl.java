package xi.ym.equip.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import xi.ym.equip.dao.RoleDaoI;
import xi.ym.equip.dao.UserDaoI;
import xi.ym.equip.domain.Prole;
import xi.ym.equip.domain.Puser;
import xi.ym.equip.pageModel.DataGrid;
import xi.ym.equip.pageModel.User;
import xi.ym.equip.service.UserServiceI;
import xi.ym.equip.util.Encrypt;

@Service("userService")
public class UserServiceImpl implements UserServiceI {
	@Resource
	private UserDaoI userDao;
	@Resource
	private RoleDaoI roleDao;

	@Override
	public User save(User user) {
		Puser p = new Puser();
		BeanUtils.copyProperties(user, p, new String[] { "userPwd", "birthday" });

		p.setUserPwd(Encrypt.e(user.getUserPwd()));
		try {
			Date birthday = DateFormat.getDateInstance().parse(user.getBirthday());
			p.setBirthday(birthday);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		userDao.save(p);
		BeanUtils.copyProperties(p, user);
		return user;
	}

	@Override
	public User login(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userPwd", Encrypt.e(user.getUserPwd()));
		params.put("username", user.getUsername());
		Puser t = userDao.get("FROM Puser p WHERE p.username = :username AND p.userPwd = :userPwd", params);
		if (t != null) {
			// id 要复制一下
			user.setId(t.getId());
			return user;
		}
		return null;
	}

	@Override
	public DataGrid usersearch(User user) {
		DataGrid dg = new DataGrid();
		StringBuffer hql = new StringBuffer("FROM Puser p ");
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(user, hql, params);

		StringBuffer totalHql = new StringBuffer("SELECT COUNT(*) " + hql);
		hql = addOrder(user, hql);

		List<Puser> l = userDao.find(hql.toString(), params, user.getPage(), user.getRows());
		List<User> nl = new ArrayList<User>();
		changeModel(l, nl);
		dg.setTotal(userDao.count(totalHql.toString(), params));
		dg.setRows(nl);
		return dg;
	}

	/**
	 * 转换PO<--->VO
	 * 
	 * @param l
	 * @param nl
	 */
	private void changeModel(List<Puser> l, List<User> nl) {
		if (l != null && l.size() > 0) {
			for (Puser p : l) {
				User u = new User();
				BeanUtils.copyProperties(p, u, new String[] { "roleName", "birthday", "userPwd" });
				StringBuffer roles = new StringBuffer("");
				String roleName = "";
				if (p.getRoles() != null && p.getRoles().size() > 0) {
					for (Prole role : p.getRoles()) {
						roles.append(role.getRoleName()).append(",");
					}
					roleName = roles.substring(0, roles.length() - 1);
				}
				u.setRoleName(roleName);
				// 处理日期类型字段
				Date birthday = p.getBirthday();
				if (birthday != null) {
					String sbirth = DateFormat.getDateInstance().format(birthday);
					u.setBirthday(sbirth);
				}
				nl.add(u);
			}
		}
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
	private StringBuffer addOrder(User user, StringBuffer hql) {
		if (user.getSort() != null) {
			hql.append(" ORDER BY " + user.getSort() + " " + user.getOrder());
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
	private StringBuffer addWhere(User user, StringBuffer hql, Map<String, Object> params) {
		if (user.getUsername() != null && !user.getUsername().trim().equals("")) {
			hql.append(" WHERE p.username LIKE :username");
			params.put("username", "%%" + user.getUsername().trim() + "%%");
		}
		return hql;
	}

	@Override
	public void remove(String ids) {
		// for (String id : ids.split(",")) {
		// Tuser u = userDao.get(Tuser.class, id);
		// if (u != null) {
		// userDao.delete(u);
		// }
		// }
		String[] nids = ids.split(",");
		StringBuffer hql = new StringBuffer("DELETE Puser p WHERE p.id IN (");
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql.append(",");
			}
			hql.append("'" + nids[i] + "'");
		}
		hql.append(")");
		userDao.executeHql(hql.toString());
	}

	@Override
	public User update(User user) {
		// 修改用户
		Puser p = userDao.get(Puser.class, user.getId());
		BeanUtils.copyProperties(user, p, new String[] { "userPwd", "birthday" });

		// 如果没有新密码则还是初始密码
		if (user.getUserPwd() == null || "".equals(user.getUserPwd())) {
			p.setUserPwd(Encrypt.e("123"));
		} else {
			p.setUserPwd(Encrypt.e(user.getUserPwd()));
		}
		try {
			Date birthday = DateFormat.getDateInstance().parse(user.getBirthday());
			p.setBirthday(birthday);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		userDao.update(p);
		BeanUtils.copyProperties(p, user);
		return user;
	}

	@Override
	public void addRole(User user, String roleIds) {
		//
		String[] nids = roleIds.split(",");
		HashSet<Prole> rs = new HashSet<Prole>();
		for (String id : nids) {
			Prole p = roleDao.get(Prole.class, Integer.parseInt(id));
			rs.add(p);
		}

		Integer uid = user.getId();
		Puser puser = userDao.get(Puser.class, uid);
		// 先删除，后更新
		puser.getRoles().clear();

		puser.setRoles(rs);
		// 更新操作
		userDao.update(puser);

	}

	@Override
	public Set<Prole> getRoles(User user) {
		//
		StringBuffer hql = new StringBuffer("FROM Puser p WHERE 1=1 AND p.id = :userID ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", user.getId());
		Puser puser = userDao.get(hql.toString(), params);
		Set<Prole> roles = puser.getRoles();
		return roles;
	}

	@Override
	public void updatePassword(User user) {
		// 修改用户
		Puser p = userDao.get(Puser.class, user.getId());

		// 如果没有新密码则还是初始密码
		if (user.getUserPwd() == null || "".equals(user.getUserPwd())) {
			p.setUserPwd(Encrypt.e("123"));
		} else {
			p.setUserPwd(Encrypt.e(user.getUserPwd()));
		}

		userDao.update(p);
	}

}
