package xi.ym.equip.pageModel;

public class ZtreeMenu implements java.io.Serializable {
	private static final long serialVersionUID = -4192338176176485437L;

	//
	private Integer pid;
	private String iconSkin;
	private String iconCls;

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	private String open;

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	//
	private Integer id;
	private String name;
	private String ourl;
	private int type;

	public String getOurl() {
		return ourl;
	}

	public void setOurl(String ourl) {
		this.ourl = ourl;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
