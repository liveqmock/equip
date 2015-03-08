package xi.ym.equip.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Peqrecord", schema = "elec_equip")
public class PeqRecord {
	private Integer id;
	private Peq eq;
	private Date eqBugtime;
	private String eqBugcase;
	private String eqBugtype;
	private Date eqRepairtime;
	private String eqRepaircase;

	@Id
	@Column(name = "EqRecordID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eqID")
	public Peq getEq() {
		return eq;
	}

	public void setEq(Peq eq) {
		this.eq = eq;
	}

	@Column(name = "eqBugtime", columnDefinition = "datetime")
	public Date getEqBugtime() {
		return eqBugtime;
	}

	public void setEqBugtime(Date eqBugtime) {
		this.eqBugtime = eqBugtime;
	}

	@Column(name = "eqBugcase")
	public String getEqBugcase() {
		return eqBugcase;
	}

	public void setEqBugcase(String eqBugcase) {
		this.eqBugcase = eqBugcase;
	}

	@Column(name = "eqBugtype")
	public String getEqBugtype() {
		return eqBugtype;
	}

	public void setEqBugtype(String eqBugtype) {
		this.eqBugtype = eqBugtype;
	}

	@Column(name = "eqRepairtime", columnDefinition = "datetime")
	public Date getEqRepairtime() {
		return eqRepairtime;
	}

	public void setEqRepairtime(Date eqRepairtime) {
		this.eqRepairtime = eqRepairtime;
	}

	@Column(name = "eqRepaircase")
	public String getEqRepaircase() {
		return eqRepaircase;
	}

	public void setEqRepaircase(String eqRepaircase) {
		this.eqRepaircase = eqRepaircase;
	}

}
