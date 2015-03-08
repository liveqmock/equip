package xi.ym.equip.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Pmenu", schema = "elec_equip")
public class Pmenu implements java.io.Serializable {

	private static final long serialVersionUID = 1318857840295890883L;
	private Integer id;
	private Pmenu pmenu;
	private String text;
	private String iconCls;
	private String url;
	private String iconSkin;
	private int type;
	private Set<Pmenu> pmenus = new HashSet<Pmenu>(0);
	private Set<Prole> roles = new HashSet<Prole>();

	public Pmenu() {
	}

	public Pmenu(Integer id) {
		this.id = id;
	}

	@Id
	@Column(name = "menuID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	public Pmenu getPmenu() {
		return this.pmenu;
	}

	public void setPmenu(Pmenu pmenu) {
		this.pmenu = pmenu;
	}

	@Column(name = "TEXT", length = 100)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "ICONCLS", length = 50)
	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconcls) {
		this.iconCls = iconcls;
	}

	@Column(name = "URL", length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Cascade(CascadeType.ALL)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pmenu")
	public Set<Pmenu> getPmenus() {
		return this.pmenus;
	}

	public void setPmenus(Set<Pmenu> pmenus) {
		this.pmenus = pmenus;
	}

	@Cascade(CascadeType.ALL)
	@ManyToMany(mappedBy = "menus", targetEntity = Prole.class)
	public Set<Prole> getRoles() {
		return roles;
	}

	public void setRoles(Set<Prole> roles) {
		this.roles = roles;
	}

	@Column(name = "type")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

}