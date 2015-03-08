package xi.ym.equip.service;

import java.util.Set;

import xi.ym.equip.domain.Prole;
import xi.ym.equip.pageModel.DataGrid;
import xi.ym.equip.pageModel.User;

public interface UserServiceI {

	public User save(User user);

	public User login(User user);

	public DataGrid usersearch(User user);

	public void remove(String ids);

	public User update(User user);

	public void addRole(User user, String roleIds);

	public Set<Prole> getRoles(User user);

	public void updatePassword(User user); 

}
