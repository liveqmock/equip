package xi.ym.equip.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;

import xi.ym.equip.pageModel.Eq;
import xi.ym.equip.pageModel.Json;
import xi.ym.equip.service.EqServiceI;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @name 设备Action
 * @description 主要完成设备这一实体的相关操作
 * @author LRB
 * 
 */
@Namespace("/")
@Action(value = "eqAction", interceptorRefs = { @InterceptorRef("mydefaultstack") })
public class EqAction extends BaseAction implements ModelDriven<Eq> {
	private static final long serialVersionUID = -6389376820243056652L;
	private Eq eq = new Eq();
	@Resource(name = "eqService")
	private EqServiceI eqService;

	@Override
	public Eq getModel() {
		return eq;
	}

	/**
	 * @name search
	 * @description 条件查询
	 */
	public void search() {
		super.writeJson(eqService.searchAllByCondition(eq));
	}

	/**
	 * @name remove
	 * @description 删除设备信息
	 */
	public void remove() {
		eqService.remove(eq.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}

	/**
	 * @name searchRecord
	 * @description 查询维修记录信息
	 */
	public void searchRecord() {
		super.writeJson(eqService.searchRecord(eq));
	}

	/**
	 * @name findAllEq
	 * @description 查询所有设备
	 */
	public void findAllEq() {
		super.writeJson(eqService.findAllEq());
	}
}
