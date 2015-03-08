package xi.ym.equip.domain;

import java.util.Date;
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

/**
 * <pre>
 * 用户实体：
 *   属性包括：
 *     用户id
 *     用户名
 *     登录密码
 *     用户权限
 * @author LRB
 * </pre>
 */
@Entity
@Table(name = "puser", schema = "elec_equip")
public class Puser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String userPwd;
	private String userAble;
	private Date birthday;
	private String telephone;

	private Set<Prole> roles = new HashSet<Prole>();

	public Puser() {
	}

	@Id
	@Column(name = "userID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "userPwd")
	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	@Column(name = "userAble")
	public String getUserAble() {
		return userAble;
	}

	public void setUserAble(String userAble) {
		this.userAble = userAble;
	}

	@Column(name = "telephone", length = 20)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "birthday", columnDefinition = "date")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@ManyToMany(targetEntity = Prole.class)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userID"), inverseJoinColumns = @JoinColumn(name = "roleID"))
	public Set<Prole> getRoles() {
		return roles;
	}

	public void setRoles(Set<Prole> roles) {
		this.roles = roles;
	}

}
