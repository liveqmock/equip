package xi.ym.equip.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pstorage", schema = "elec_equip")
public class Pstorage {

	private Integer id;
	private int eqNumb;
	private int storageNumb;
	private String storageParam;
	private String storageW;
	private String storageS;

	@Id
	@Column(name = "StorageID", unique = true, nullable = false, length = 36)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "EqNumb", columnDefinition = "int")
	public int getEqNumb() {
		return eqNumb;
	}

	public void setEqNumb(int eqNumb) {
		this.eqNumb = eqNumb;
	}

	@Column(name = "StorageNumb", columnDefinition = "int")
	public int getStorageNumb() {
		return storageNumb;
	}

	public void setStorageNumb(int storageNumb) {
		this.storageNumb = storageNumb;
	}

	@Column(name = "StorageParam")
	public String getStorageParam() {
		return storageParam;
	}

	public void setStorageParam(String storageParam) {
		this.storageParam = storageParam;
	}

	@Column(name = "storageW")
	public String getStorageW() {
		return storageW;
	}

	public void setStorageW(String storageW) {
		this.storageW = storageW;
	}

	@Column(name = "storageS")
	public String getStorageS() {
		return storageS;
	}

	public void setStorageS(String storageS) {
		this.storageS = storageS;
	}

}
