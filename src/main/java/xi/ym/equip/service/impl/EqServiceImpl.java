package xi.ym.equip.service.impl;

import java.text.DateFormat;
import java.util.ArrayList;
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
import xi.ym.equip.pageModel.Eq;
import xi.ym.equip.pageModel.EqRecord;
import xi.ym.equip.service.EqServiceI;

@Service("eqService")
public class EqServiceImpl implements EqServiceI {
	@Resource(name = "eqDao")
	private EqDaoI eqDao;
	@Resource(name = "eqRecordDao")
	private EqRecordDaoI eqRecordDao;

	@Override
	public DataGrid searchAllByCondition(Eq eq) {
		DataGrid dg = new DataGrid();
		StringBuffer hql = new StringBuffer("FROM Peq p ");
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(eq, hql, params);
		StringBuffer totalHql = new StringBuffer("SELECT COUNT(*) " + hql);
		hql = addOrder(eq, hql);
		List<Peq> l = eqDao.find(hql.toString(), params, eq.getPage(), eq.getRows());
		List<Eq> nl = new ArrayList<Eq>();
		changeModel(l, nl);
		dg.setTotal(eqDao.count(totalHql.toString(), params));
		dg.setRows(nl);
		return dg;
	}

	/**
	 * 装换PO<--->VO
	 * 
	 * @param l
	 * @param nl
	 */
	private void changeModel(List<Peq> l, List<Eq> nl) {
		if (l != null && l.size() > 0) {
			for (Peq p : l) {
				Eq s = new Eq();
				BeanUtils.copyProperties(p, s, new String[] { "eqRepairN", "eqBugN", "eqYear" });
				// 这些类型不能自动转换
				try {
					s.setEqBugN(String.valueOf((p.getEqBugN())));
					s.setEqRepairN(String.valueOf(p.getEqRepairN()));
					if (p.getEqYear() != null)
						s.setEqYear(DateFormat.getDateInstance().format(p.getEqYear()));
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
	 * @param eq
	 *            VO
	 * @param hql
	 *            传入hql
	 * @return 拼装之后的hql
	 */
	private StringBuffer addOrder(Eq eq, StringBuffer hql) {
		if (eq.getSort() != null) {
			hql.append(" ORDER BY " + eq.getSort() + " " + eq.getOrder());
		}
		return hql;
	}

	/**
	 * 添加过滤条件
	 * 
	 * @param eq
	 *            VO
	 * @param hql
	 *            hql
	 * @param params
	 *            hql
	 * @return 拼装之后 hql
	 */
	private StringBuffer addWhere(Eq eq, StringBuffer hql, Map<String, Object> params) {
		if (eq.getEqName() != null && !eq.getEqName().trim().equals("")) {
			hql.append(" WHERE p.eqName LIKE :eqName");
			params.put("eqName", "%%" + eq.getEqName().trim() + "%%");
		}
		return hql;
	}

	@Override
	public void remove(String ids) {
		String[] nids = ids.split(",");
		StringBuffer hql = new StringBuffer("DELETE Peq p WHERE p.id IN (");
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql.append(",");
			}
			hql.append("'" + nids[i] + "'");
		}
		hql.append(")");
		eqDao.executeHql(hql.toString());
	}

	@Override
	public DataGrid searchRecord(Eq eq) {
		DataGrid dg = new DataGrid();
		StringBuffer hql = new StringBuffer("FROM PeqRecord p where p.eq.id = ");
		hql.append(eq.getId());

		List<PeqRecord> l = eqRecordDao.find(hql.toString());
		List<EqRecord> nl = new ArrayList<EqRecord>();
		changeModelPeqRecord2EqRecord(l, nl);

		dg.setRows(nl);
		return dg;
	}

	private void changeModelPeqRecord2EqRecord(List<PeqRecord> l, List<EqRecord> nl) {
		if (l != null && l.size() > 0) {
			for (PeqRecord p : l) {
				EqRecord s = new EqRecord();
				BeanUtils.copyProperties(p, s, new String[] { "eqBugtime", "eqRepairtime" });
				// 这些类型不能自动转换
				try {
					s.setEqName(p.getEq().getEqName());
					if (p.getEqBugtime() != null)
						s.setEqBugtime(DateFormat.getDateInstance().format(p.getEqBugtime()));
					if (p.getEqRepairtime() != null)
						s.setEqRepairtime(DateFormat.getDateInstance().format(p.getEqRepairtime()));
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				nl.add(s);
			}
		}
	}

	@Override
	public List<Eq> findAllEq() {
		String hql = "FROM Peq p ";
		List<Peq> l = eqDao.find(hql);
		List<Eq> nl = new ArrayList<Eq>();
		changeModel(l, nl);
		return nl;
	}

}
