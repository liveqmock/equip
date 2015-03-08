package xi.ym.equip.action;

import org.apache.log4j.Logger;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import xi.ym.equip.pageModel.Json;
import xi.ym.equip.pageModel.Storage;
import xi.ym.equip.service.StorageServiceI;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @name 库存Action
 * @description 主要完成库存这一实体的相关操作
 * @author LRB
 * 
 */
@Action(value = "storageAction", interceptorRefs = { @InterceptorRef("mydefaultstack") })
@Namespace("/")
public class StorageAction extends BaseAction implements ModelDriven<Storage> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(StorageAction.class);

	private static final long serialVersionUID = 5093137191987432034L;
	private Storage storage = new Storage();

	public StorageServiceI getStorageService() {
		return storageService;
	}

	@Autowired
	public void setStorageService(StorageServiceI storageService) {
		this.storageService = storageService;
	}

	private StorageServiceI storageService;

	@Override
	public Storage getModel() {
		return storage;
	}

	/**
	 * @name inList
	 * @description 入库记录
	 */
	public void inList() {
		super.writeJson(storageService.inList(storage));
	}

	/**
	 * @name outList
	 * @description 出库记录
	 */
	public void outList() {
		super.writeJson(storageService.outList(storage));
	}

	/**
	 * @name inputData
	 * @description 入库
	 */
	public void inputData() {
		storageService.inputData(storage.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("入库成功！");
		super.writeJson(j);
	}

	/**
	 * @name outputData
	 * @description 出库
	 */
	public void outputData() {
		logger.info("机房号：" + storage.getStorageNumb());
		storageService.outputData(storage.getIds(), storage.getStorageNumb());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("出库成功！");
		super.writeJson(j);
	}

}
