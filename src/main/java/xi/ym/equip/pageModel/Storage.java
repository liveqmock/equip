package xi.ym.equip.pageModel;

public class Storage implements java.io.Serializable {
	private static final long serialVersionUID = 3261164474601454939L;
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
	private String eqNumb;
	private String storageNumb;
	private String storageParam;
	private String storageW;
	private String storageS;

	public String getStorageW() {
		return storageW;
	}

	public void setStorageW(String storageW) {
		this.storageW = storageW;
	}

	public String getStorageS() {
		return storageS;
	}

	public void setStorageS(String storageS) {
		this.storageS = storageS;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStorageParam() {
		return storageParam;
	}

	public String getEqNumb() {
		return eqNumb;
	}

	public void setEqNumb(String eqNumb) {
		this.eqNumb = eqNumb;
	}

	public String getStorageNumb() {
		return storageNumb;
	}

	public void setStorageNumb(String storageNumb) {
		this.storageNumb = storageNumb;
	}

	public void setStorageParam(String storageParam) {
		this.storageParam = storageParam;
	}

}
