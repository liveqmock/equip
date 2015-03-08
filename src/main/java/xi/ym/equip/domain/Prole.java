package xi.ym.equip.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "prole", schema = "elec_equip")
public class Prole implements java.io.Serializable {
	private static final long serialVersionUID = 2380388330011883842L;
	private Integer id;
	private String roleName;
	private String roleDesc;

	private Set<Puser> users = new HashSet<Puser>();
	private Set<Pmenu> menus = new HashSet<Pmenu>();

	@Id
	@Column(name = "roleID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "rolename")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "roleDesc")
	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@ManyToMany(mappedBy = "roles", targetEntity = Puser.class)
	public Set<Puser> getUsers() {
		return users;
	}

	public void setUsers(Set<Puser> users) {
		this.users = users;
	}

	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@ManyToMany(targetEntity = Pmenu.class)
	@JoinTable(name = "role_menu", joinColumns = @JoinColumn(name = "roleID"), inverseJoinColumns = @JoinColumn(name = "menuID"))
	public Set<Pmenu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Pmenu> menus) {
		this.menus = menus;
	}

}
