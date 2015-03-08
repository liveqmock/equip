package xi.ym.equip.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;

import xi.ym.equip.pageModel.EqRecord;
import xi.ym.equip.pageModel.Json;
import xi.ym.equip.service.EqRecordServiceI;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @name 设备维修记录Action
 * @description 主要完成设备记录这一实体的相关操作
 * @author LRB
 * 
 */
@Action(value = "eqRecordAction", interceptorRefs = { @InterceptorRef("mydefaultstack") })
@Namespace("/")
public class EqRecordAction extends BaseAction implements ModelDriven<EqRecord> {
	private static final long serialVersionUID = 5112524955176988194L;
	private EqRecord eqRecord = new EqRecord();
	@Resource(name = "eqRecordService")
	private EqRecordServiceI eqRecordService;

	@Override
	public EqRecord getModel() {
		return eqRecord;
	}

	/**
	 * @name search
	 * @description 条件查询
	 */
	public void search() {
		super.writeJson(eqRecordService.searchAllByCondition(eqRecord));
	}

	/**
	 * @name add
	 * @description 添加记录
	 */
	public void add() {
		Json j = new Json();
		try {
			EqRecord eqR = eqRecordService.save(eqRecord);

			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(eqR);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		super.writeJson(j);
	}

	public void searchByType() {
		super.writeJson(eqRecordService.searchAllByType(eqRecord));
	}

	public void fix() {
		Json j = new Json();
		try {
			EqRecord eqR = eqRecordService.saveFixInfo(eqRecord);

			j.setSuccess(true);
			j.setMsg("维修完成！");
			j.setObj(eqR);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		super.writeJson(j);
	}

	public void searchFix() {
		super.writeJson(eqRecordService.searchFix(eqRecord));
	}
	
	public void searchWarning() {
		super.writeJson(eqRecordService.searchWarning());
	}
}
