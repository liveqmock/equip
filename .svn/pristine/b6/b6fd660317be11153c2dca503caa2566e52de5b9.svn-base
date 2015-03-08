package xi.ym.equip.service;

import xi.ym.equip.pageModel.DataGrid;
import xi.ym.equip.pageModel.Select;

public interface SelectServiceI {
	/**
	 * 提交申请
	 * 
	 * @param select
	 *            VO对象
	 * @return VO 返回
	 */
	public Select apply(Select select);

	/**
	 * 条件 查询
	 * 
	 * @param select
	 *            VO对象
	 * @return DataGrid
	 */
	public DataGrid searchAllByCondition(Select select);

	/**
	 * 删除记录
	 * 
	 * @param ids
	 */
	public void remove(String ids);

	/**
	 * 审批通过
	 * 
	 * @param select
	 *            VO对象
	 */
	public void approveOk(Select select);

	/**
	 * 查询待下单的记录
	 * 
	 * @param select
	 *            VO对象
	 * @return DataGrid
	 */
	public DataGrid searchForSubmit(Select select);

	/**
	 * 提交申请
	 * 
	 * @param select
	 *            VO对象
	 */
	public void submitList(Select select);

	/**
	 * 查询待签订合同的记录
	 * 
	 * @param select
	 *            VO对象
	 * @return DataGrid
	 */
	public DataGrid searchForSign(Select select);

	/**
	 * 合同签订
	 * 
	 * @param select
	 *            VO对象
	 */
	public void dealsign(Select select);

	/**
	 * 查询待查的记录
	 * 
	 * @param select
	 *            VO对象
	 * @return DataGrid
	 */
	public DataGrid searchForCheck(Select select);

	/**
	 * 设备统计审查
	 * 
	 * @param select
	 *            VO对象
	 */
	public void equipCheck(Select select);

	/**
	 * 查询待采购的设备
	 * 
	 * @param select
	 *            VO对象
	 * @return DataGrid
	 */
	public DataGrid searchForPurchase(Select select);
}
