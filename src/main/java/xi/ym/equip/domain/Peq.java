package xi.ym.equip.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Peq", schema = "elec_equip")
public class Peq {
	private Integer id;
	private String eqName;
	private String eqType;
	private String eqPact;
	private String eqParam;
	private int eqBugN;
	private int eqRepairN;
	private String eqState;
	private String eqInfo;
	private String eqVender;
	private Date eqYear;

	private Set<PeqRecord> eqRecords = new HashSet<PeqRecord>();

	@Id
	@Column(name = "eqID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "eqName")
	public String getEqName() {
		return eqName;
	}

	public void setEqName(String eqName) {
		this.eqName = eqName;
	}

	@Column(name = "eqType")
	public String getEqType() {
		return eqType;
	}

	public void setEqType(String eqType) {
		this.eqType = eqType;
	}

	@Column(name = "eqPact")
	public String getEqPact() {
		return eqPact;
	}

	public void setEqPact(String eqPact) {
		this.eqPact = eqPact;
	}

	@Column(name = "eqParam")
	public String getEqParam() {
		return eqParam;
	}

	public void setEqParam(String eqParam) {
		this.eqParam = eqParam;
	}

	@Column(name = "eqbugN", columnDefinition = "int")
	public int getEqBugN() {
		return eqBugN;
	}

	public void setEqBugN(int eqBugN) {
		this.eqBugN = eqBugN;
	}

	@Column(name = "eqRepairN", columnDefinition = "int")
	public int getEqRepairN() {
		return eqRepairN;
	}

	public void setEqRepairN(int eqRepairN) {
		this.eqRepairN = eqRepairN;
	}

	@Column(name = "EqState")
	public String getEqState() {
		return eqState;
	}

	public void setEqState(String eqState) {
		this.eqState = eqState;
	}

	@Column(name = "eqInfo")
	public String getEqInfo() {
		return eqInfo;
	}

	public void setEqInfo(String eqInfo) {
		this.eqInfo = eqInfo;
	}

	@Column(name = "eqVender")
	public String getEqVender() {
		return eqVender;
	}

	public void setEqVender(String eqVender) {
		this.eqVender = eqVender;
	}

	@Column(name = "eqYear", columnDefinition = "datetime")
	public Date getEqYear() {
		return eqYear;
	}

	public void setEqYear(Date eqYear) {
		this.eqYear = eqYear;
	}

	@OneToMany(mappedBy = "eq", targetEntity = PeqRecord.class)
	public Set<PeqRecord> getEqRecords() {
		return eqRecords;
	}

	public void setEqRecords(Set<PeqRecord> eqRecords) {
		this.eqRecords = eqRecords;
	}

}
