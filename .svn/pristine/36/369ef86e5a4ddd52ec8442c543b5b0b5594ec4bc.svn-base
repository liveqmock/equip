package xi.ym.equip.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;

import xi.ym.equip.pageModel.Json;
import xi.ym.equip.pageModel.Select;
import xi.ym.equip.service.SelectServiceI;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @name 采购申请Action
 * @description 主要完成采购申请这一实体的相关操作
 * @author LRB
 * 
 */
@Action(value = "selectAction", interceptorRefs = { @InterceptorRef("mydefaultstack") })
@Namespace("/")
public class SelectAction extends BaseAction implements ModelDriven<Select> {
	private static final long serialVersionUID = -7583220979271728245L;
	private Select select = new Select();
	@Resource(name = "selectService")
	private SelectServiceI selectService;

	@Override
	public Select getModel() {
		return select;
	}

	/**
	 * @name apply
	 * @description 提交申请
	 */
	public void apply() {
		Json j = new Json();
		try {
			selectService.apply(select);
			j.setSuccess(true);
			j.setMsg("添加成功！");

		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}

		super.writeJson(j);
	}

	/**
	 * @name search
	 * @description 条件查询
	 */
	public void search() {
		super.writeJson(selectService.searchAllByCondition(select));
	}

	/**
	 * @name remove
	 * @description 删除记录
	 */
	public void remove() {
		selectService.remove(select.getIds());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}

	/**
	 * @name approveOk
	 * @description 审批
	 */
	public void approveOk() {
		selectService.approveOk(select);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("审批成功！");
		super.writeJson(j);
	}

	/**
	 * @name searchForSubmit
	 * @description 查询待下单的记录
	 */
	public void searchForSubmit() {
		super.writeJson(selectService.searchForSubmit(select));
	}

	/**
	 * @name searchForSign
	 * @description 查询待签订合同的记录
	 */
	public void searchForSign() {
		super.writeJson(selectService.searchForSign(select));
	}

	/**
	 * @name submitList
	 * @description 提交下单
	 */
	public void submitList() {
		selectService.submitList(select);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("下单成功！");
		super.writeJson(j);
	}

	/**
	 * @name dealsign
	 * @description 合同签订
	 */
	public void dealsign() {
		selectService.dealsign(select);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("合同签订成功！");
		super.writeJson(j);
	}

	/**
	 * @name searchForCheck
	 * @description 查询待验收的记录
	 */
	public void searchForCheck() {
		super.writeJson(selectService.searchForCheck(select));
	}

	/**
	 * @name equipCheck
	 * @description 合同验收
	 */
	public void equipCheck() {
		selectService.equipCheck(select);

		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("合同验收成功！");
		super.writeJson(j);
	}

	/**
	 * @name searchForPurchase
	 * @description 查询待采购的信息
	 */
	public void searchForPurchase() {
		super.writeJson(selectService.searchForPurchase(select));
	}

}
