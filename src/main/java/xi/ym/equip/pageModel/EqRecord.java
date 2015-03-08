package xi.ym.equip.pageModel;

public class EqRecord {
	private int page;
	private int rows;
	private String sort;
	private String order;
	private String ids;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	private Integer id;
	private Integer eqId;
	private String eqName;
	private String eqBugtime;
	private String eqBugcase;
	private String eqBugtype;
	private String eqRepairtime;
	private String eqRepaircase;

	public Integer getEqId() {
		return eqId;
	}

	public void setEqId(Integer eqId) {
		this.eqId = eqId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEqName() {
		return eqName;
	}

	public void setEqName(String eqName) {
		this.eqName = eqName;
	}

	public String getEqBugtime() {
		return eqBugtime;
	}

	public void setEqBugtime(String eqBugtime) {
		this.eqBugtime = eqBugtime;
	}

	public String getEqBugcase() {
		return eqBugcase;
	}

	public void setEqBugcase(String eqBugcase) {
		this.eqBugcase = eqBugcase;
	}

	public String getEqBugtype() {
		return eqBugtype;
	}

	public void setEqBugtype(String eqBugtype) {
		this.eqBugtype = eqBugtype;
	}

	public String getEqRepairtime() {
		return eqRepairtime;
	}

	public void setEqRepairtime(String eqRepairtime) {
		this.eqRepairtime = eqRepairtime;
	}

	public String getEqRepaircase() {
		return eqRepaircase;
	}

	public void setEqRepaircase(String eqRepaircase) {
		this.eqRepaircase = eqRepaircase;
	}

}
