package xi.ym.equip.pageModel;

public class Eq implements java.io.Serializable {
	private static final long serialVersionUID = 7549817682984935684L;
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
	private String eqName;
	private String eqType;
	private String eqPact;
	private String eqParam;
	private String eqBugN;
	private String eqRepairN;
	private String eqState;
	private String eqInfo;
	private String eqVender;
	private String eqYear;

	public String getEqYear() {
		return eqYear;
	}

	public void setEqYear(String eqYear) {
		this.eqYear = eqYear;
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

	public String getEqType() {
		return eqType;
	}

	public void setEqType(String eqType) {
		this.eqType = eqType;
	}

	public String getEqPact() {
		return eqPact;
	}

	public void setEqPact(String eqPact) {
		this.eqPact = eqPact;
	}

	public String getEqParam() {
		return eqParam;
	}

	public void setEqParam(String eqParam) {
		this.eqParam = eqParam;
	}

	public String getEqBugN() {
		return eqBugN;
	}

	public void setEqBugN(String eqBugN) {
		this.eqBugN = eqBugN;
	}

	public String getEqRepairN() {
		return eqRepairN;
	}

	public void setEqRepairN(String eqRepairN) {
		this.eqRepairN = eqRepairN;
	}

	public String getEqState() {
		return eqState;
	}

	public void setEqState(String eqState) {
		this.eqState = eqState;
	}

	public String getEqInfo() {
		return eqInfo;
	}

	public void setEqInfo(String eqInfo) {
		this.eqInfo = eqInfo;
	}

	public String getEqVender() {
		return eqVender;
	}

	public void setEqVender(String eqVender) {
		this.eqVender = eqVender;
	}

}
