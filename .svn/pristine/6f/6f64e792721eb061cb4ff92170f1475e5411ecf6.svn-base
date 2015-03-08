package xi.ym.equip.service;

import xi.ym.equip.pageModel.DataGrid;
import xi.ym.equip.pageModel.Storage;

public interface StorageServiceI {
	/**
	 * 入库操作
	 * 
	 * @param ids
	 */
	void inputData(String ids);

	/**
	 * 出库操作
	 * 
	 * @param ids
	 *            设备ids
	 * @param storageNumb
	 *            出库的机房号
	 */

	void outputData(String ids, String storageNumb);

	/**
	 * 入库统计
	 * 
	 * @param storage
	 *            VO
	 */
	DataGrid inList(Storage storage);

	/**
	 * 出库统计
	 * 
	 * @param storage
	 *            VO
	 */
	DataGrid outList(Storage storage);

}
