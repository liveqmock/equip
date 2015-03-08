package xi.ym.equip.service;

import xi.ym.equip.pageModel.DataGrid;
import xi.ym.equip.pageModel.EqRecord;

public interface EqRecordServiceI {
	/**
	 * @description 条件查询
	 * 
	 * @param eqRecord
	 *            VO对象
	 * @return DataGrid 可以转成json
	 */
	DataGrid searchAllByCondition(EqRecord eqRecord);

	/**
	 * @description 保存信息
	 * 
	 * @param eqRecord
	 *            VO对象
	 */
	EqRecord save(EqRecord eqRecord);

	/**
	 * <pre>
	 * <p>Description:</p>
	 * @version 1.0
	 * @date 2015年2月24日 
	 * @author LRB
	 * @param eqRecord
	 * @return
	 * </pre>
	 */
	DataGrid searchAllByType(EqRecord eqRecord);

	/**
	 * <pre>
	 * <p>Description:</p>
	 * @version 1.0
	 * @date 2015年2月24日 
	 * @author LRB
	 * @param eqRecord
	 * @return
	 * </pre>
	 */
	EqRecord saveFixInfo(EqRecord eqRecord);

	/**
	 * <pre>
	 * <p>Description:</p>
	 * @version 1.0
	 * @date 2015年2月24日 
	 * @author LRB
	 * @param eqRecord
	 * @return
	 * </pre>
	 */
	Object searchFix(EqRecord eqRecord);

	/**
	 * <pre>
	 * <p>Description:</p>
	 * @version 1.0
	 * @date 2015年2月27日
	 * @author LRB
	 * @param eqRecord
	 * @return
	 * </pre>
	 */
	Object searchWarning();

}
