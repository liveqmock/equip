package xi.ym.equip.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pSelect", schema = "elec_equip")
public class Pselect implements java.io.Serializable {
	private static final long serialVersionUID = -1862636561550129586L;

	private Integer id;
	private String selectName;
	private Date selectYear;
	private String selectType;
	private String selectParam;
	private String selectInfo;
	private String selectApprove;
	private Integer selectCount;
	private String selectAccept;
	private String selectStat;
	private String selectVender;

	@Id
	@Column(name = "selectID", unique = true, nullable = false, length = 36)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "selectName")
	public String getSelectName() {
		return selectName;
	}

	public void setSelectName(String selectName) {
		this.selectName = selectName;
	}

	@Column(name = "selectYear")
	public Date getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(Date selectYear) {
		this.selectYear = selectYear;
	}

	@Column(name = "selectType")
	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	@Column(name = "selectParam")
	public String getSelectParam() {
		return selectParam;
	}

	public void setSelectParam(String selectParam) {
		this.selectParam = selectParam;
	}

	@Column(name = "selectInfo")
	public String getSelectInfo() {
		return selectInfo;
	}

	public void setSelectInfo(String selectInfo) {
		this.selectInfo = selectInfo;
	}

	@Column(name = "selectApprove")
	public String getSelectApprove() {
		return selectApprove;
	}

	public void setSelectApprove(String selectApprove) {
		this.selectApprove = selectApprove;
	}

	@Column(name = "SelectAccept")
	public String getSelectAccept() {
		return selectAccept;
	}

	public void setSelectAccept(String selectAccept) {
		this.selectAccept = selectAccept;
	}

	@Column(name = "SelectStat")
	public String getSelectStat() {
		return selectStat;
	}

	public void setSelectStat(String selectStat) {
		this.selectStat = selectStat;
	}

	@Column(name = "SelectVender")
	public String getSelectVender() {
		return selectVender;
	}

	public void setSelectVender(String selectVender) {
		this.selectVender = selectVender;
	}

	@Column(name = "SelectCount")
	public Integer getSelectCount() {
		return selectCount;
	}

	public void setSelectCount(Integer selectCount) {
		this.selectCount = selectCount;
	}

}
