package xi.ym.equip.service.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import xi.ym.equip.dao.EqDaoI;
import xi.ym.equip.dao.EqRecordDaoI;
import xi.ym.equip.domain.Peq;
import xi.ym.equip.domain.PeqRecord;
import xi.ym.equip.pageModel.DataGrid;
import xi.ym.equip.pageModel.EqRecord;
import xi.ym.equip.service.EqRecordServiceI;

@Service("eqRecordService")
public class EqRecordServiceImpl implements EqRecordServiceI {
	@Resource(name = "eqRecordDao")
	private EqRecordDaoI eqRecordDao;
	@Resource(name = "eqDao")
	private EqDaoI eqDao;

	@Override
	public DataGrid searchAllByCondition(EqRecord eqRecord) {
		DataGrid dg = new DataGrid();
		StringBuffer hql = new StringBuffer("FROM PeqRecord p ");
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(eqRecord, hql, params);
		StringBuffer totalHql = new StringBuffer("SELECT COUNT(*) " + hql);
		hql = addOrder(eqRecord, hql);
		List<PeqRecord> l = eqRecordDao.find(hql.toString(), params, eqRecord.getPage(), eqRecord.getRows());
		List<EqRecord> nl = new ArrayList<EqRecord>();
		changeModel(l, nl);
		dg.setTotal(eqRecordDao.count(totalHql.toString(), params));
		dg.setRows(nl);
		return dg;
	}

	/**
	 * 装换PO<--->VO
	 * 
	 * @param l
	 * @param nl
	 */
	private void changeModel(List<PeqRecord> l, List<EqRecord> nl) {
		if (l != null && l.size() > 0) {
			for (PeqRecord p : l) {
				EqRecord s = new EqRecord();
				BeanUtils.copyProperties(p, s, new String[] { "eqBugtime", "eqRepairtime" });
				// 这些类型不能自动转换
				try {
					s.setEqName(p.getEq().getEqName());
					if (p.getEqBugtime() != null)
						s.setEqBugtime(DateFormat.getDateTimeInstance().format(p.getEqBugtime()));
					if (p.getEqRepairtime() != null)
						s.setEqRepairtime(DateFormat.getDateTimeInstance().format(p.getEqRepairtime()));
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				nl.add(s);
			}
		}
	}

	/**
	 * 添加排序条件
	 * 
	 * @param eqRecord
	 *            VO
	 * @param hql
	 *            传入hql
	 * @return 拼装之后的hql
	 */
	private StringBuffer addOrder(EqRecord eqRecord, StringBuffer hql) {
		if (eqRecord.getSort() != null && !"eqName".equals("eqName")) {
			hql.append(" ORDER BY " + eqRecord.getSort() + " " + eqRecord.getOrder());
		} else if ("eqName".equals("eqName")) {
			hql.append(" ORDER BY " + " p.eq.eqName " + " " + eqRecord.getOrder());
		}
		return hql;
	}

	/**
	 * 添加过滤条件
	 * 
	 * @param eqRecord
	 *            VO
	 * @param hql
	 *            hql
	 * @param params
	 *            hql
	 * @return 拼装之后 hql
	 */
	private StringBuffer addWhere(EqRecord eqRecord, StringBuffer hql, Map<String, Object> params) {
		if (eqRecord.getEqName() != null && !eqRecord.getEqName().trim().equals("")) {
			hql.append(" AND p.eq.eqName LIKE :eqName");
			params.put("eqName", "%%" + eqRecord.getEqName().trim() + "%%");
		}
		if (eqRecord.getEqBugtype() != null && !eqRecord.getEqBugtype().trim().equals("")) {
			hql.append(" AND p.eqBugtype LIKE :eqBugtype");
			params.put("eqBugtype", "%%" + eqRecord.getEqBugtype().trim() + "%%");
		}
		return hql;
	}

	@Override
	public EqRecord save(EqRecord eqRecord) {
		//
		PeqRecord p = new PeqRecord();
		BeanUtils.copyProperties(eqRecord, p, new String[] { "eqBugtime", "eqRepairtime" });

		// 不能自动转换的字段
		try {
			if (eqRecord.getEqId() != null && 0 != eqRecord.getEqId()) {
				Peq peq = eqDao.get(Peq.class, eqRecord.getEqId());
				p.setEq(peq);
			}
			if (eqRecord.getEqBugtime() != null && !"".equals(eqRecord.getEqBugtime()))
				p.setEqBugtime(DateFormat.getDateInstance().parse(eqRecord.getEqBugtime()));
			if (eqRecord.getEqRepairtime() != null && !"".equals(eqRecord.getEqRepairtime()))
				p.setEqRepairtime(DateFormat.getDateInstance().parse(eqRecord.getEqRepairtime()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		eqRecordDao.save(p);
		// 处理不能自动转换的字段
		BeanUtils.copyProperties(p, eqRecord, new String[] { "eqBugtime", "eqRepairtime" });
		try {
			if (p.getEqBugtime() != null)
				eqRecord.setEqBugtime(DateFormat.getDateInstance().format(p.getEqBugtime()));
			if (p.getEqRepairtime() != null)
				eqRecord.setEqRepairtime(DateFormat.getDateInstance().format(p.getEqRepairtime()));
			if (p.getEq() != null) {
				eqRecord.setEqName(p.getEq().getEqName());
				eqRecord.setEqId(p.getEq().getId());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return eqRecord;
	}

	@Override
	public DataGrid searchAllByType(EqRecord eqRecord) {
		DataGrid dg = new DataGrid();
		StringBuffer hql = new StringBuffer("FROM PeqRecord p WHERE 1=1 ");
		hql.append(" AND p.eqRepairtime IS  NULL ")//
				.append(" AND p.eqRepaircase IS  NULL ");
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(eqRecord, hql, params);
		StringBuffer totalHql = new StringBuffer("SELECT COUNT(*) " + hql);
		hql = addOrder(eqRecord, hql);
		List<PeqRecord> l = eqRecordDao.find(hql.toString(), params, eqRecord.getPage(), eqRecord.getRows());
		List<EqRecord> nl = new ArrayList<EqRecord>();
		changeModel(l, nl);
		dg.setTotal(eqRecordDao.count(totalHql.toString(), params));
		dg.setRows(nl);
		return dg;
	}

	@Override
	public EqRecord saveFixInfo(EqRecord eqRecord) {
		//
		PeqRecord peqRecord = null;
		if (eqRecord.getId() != null) {
			peqRecord = eqRecordDao.get(PeqRecord.class, eqRecord.getId());
		}
		// 更新字段
		// 处理日期
		peqRecord.setEqRepairtime(new Date());
		peqRecord.setEqRepaircase(eqRecord.getEqRepaircase());

		// 完成更新操作
		eqRecordDao.update(peqRecord);
		// 处理不能自动转换的字段
		// BeanUtils.copyProperties(peqRecord, eqRecord, new String[] { "eqBugtime", "eqRepairtime" });
		// try {
		// if (peqRecord.getEqBugtime() != null)
		// eqRecord.setEqBugtime(DateFormat.getDateInstance().format(peqRecord.getEqBugtime()));
		// if (peqRecord.getEqRepairtime() != null)
		// eqRecord.setEqRepairtime(DateFormat.getDateInstance().format(peqRecord.getEqRepairtime()));
		// if (peqRecord.getEq() != null) {
		// eqRecord.setEqName(peqRecord.getEq().getEqName());
		// eqRecord.setEqId(peqRecord.getEq().getId());
		// }
		// } catch (Exception e) {
		// throw new RuntimeException(e);
		// }
		return eqRecord;
	}

	@Override
	public Object searchFix(EqRecord eqRecord) {
		DataGrid dg = new DataGrid();
		StringBuffer hql = new StringBuffer("FROM PeqRecord p WHERE 1=1 ");
		hql.append(" AND p.eqRepairtime IS NOT NULL ")//
				.append(" AND p.eqRepaircase IS NOT NULL ");
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(eqRecord, hql, params);
		StringBuffer totalHql = new StringBuffer("SELECT COUNT(*) " + hql);
		hql = addOrder(eqRecord, hql);
		List<PeqRecord> l = eqRecordDao.find(hql.toString(), params, eqRecord.getPage(), eqRecord.getRows());
		List<EqRecord> nl = new ArrayList<EqRecord>();
		changeModel(l, nl);
		dg.setTotal(eqRecordDao.count(totalHql.toString(), params));
		dg.setRows(nl);
		return dg;
	}

	@Override
	public Object searchWarning() {
		DataGrid dg = new DataGrid();
		StringBuffer hql = new StringBuffer("FROM PeqRecord p WHERE 1=1 ");

		hql.append(" AND p.eqRepairtime IS NOT NULL ")//
				.append(" AND p.eqRepaircase='已报废' ");
		StringBuffer totalHql = new StringBuffer("SELECT COUNT(*) " + hql);
		List<PeqRecord> l = eqRecordDao.find(hql.toString());
		List<EqRecord> nl = new ArrayList<EqRecord>();
		//
		changeModel(l, nl);
		dg.setTotal(eqRecordDao.count(totalHql.toString()));
		dg.setRows(nl);
		return dg;
	}
}
