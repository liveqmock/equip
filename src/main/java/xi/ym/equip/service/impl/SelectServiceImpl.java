package xi.ym.equip.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import xi.ym.equip.dao.EqDaoI;
import xi.ym.equip.dao.SelectDaoI;
import xi.ym.equip.dao.UserDaoI;
import xi.ym.equip.domain.Peq;
import xi.ym.equip.domain.Pselect;
import xi.ym.equip.pageModel.DataGrid;
import xi.ym.equip.pageModel.Select;
import xi.ym.equip.service.SelectServiceI;

@Service("selectService")
public class SelectServiceImpl implements SelectServiceI {

	@Resource(name = "selectDao")
	private SelectDaoI selectDao;
	@Resource(name = "eqDao")
	private EqDaoI eqDao;
	@Resource(name = "userDao")
	private UserDaoI userDao;

	@Override
	public Select apply(Select select) {
		Pselect s = new Pselect();
		BeanUtils.copyProperties(select, s, new String[] { "selectYear" });
		// 处理 不能自动转换的日期格式
		try {
			s.setSelectYear(DateFormat.getDateInstance().parse(select.getSelectYear()));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		selectDao.save(s);
		return select;
	}

	@Override
	public DataGrid searchAllByCondition(Select select) {
		//
		DataGrid dg = new DataGrid();
		StringBuffer hql = new StringBuffer("FROM Pselect p WHERE 1=1 ");
		hql.append(" AND p.selectApprove IS NULL")//
				.append(" AND p.selectCount IS NULL")//
				.append(" AND p.selectAccept IS NULL")//
				.append(" AND p.selectStat IS NULL")//
				.append(" AND p.selectVender IS NULL");
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(select, hql, params);
		StringBuffer totalHql = new StringBuffer("SELECT COUNT(*) " + hql);
		hql = addOrder(select, hql);
		// System.out.println(hql);
		List<Pselect> l = selectDao.find(hql.toString(), params, select.getPage(), select.getRows());
		List<Select> nl = new ArrayList<Select>();
		changeModel(l, nl);
		dg.setTotal(selectDao.count(totalHql.toString(), params));
		dg.setRows(nl);
		return dg;
	}

	/**
	 * 装换PO<--->VO
	 * 
	 * @param l
	 * @param nl
	 */
	private void changeModel(List<Pselect> l, List<Select> nl) {
		if (l != null && l.size() > 0) {
			for (Pselect p : l) {
				Select s = new Select();
				BeanUtils.copyProperties(p, s, new String[] { "selectYear" });
				try {
					if (p.getSelectYear() != null)
						s.setSelectYear(DateFormat.getDateInstance().format(p.getSelectYear()));
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
	 * @param select
	 *            VO
	 * @param hql
	 *            传入hql
	 * @return 拼装之后的hql
	 */
	private StringBuffer addOrder(Select select, StringBuffer hql) {
		if (select.getSort() != null) {
			hql.append(" ORDER BY " + select.getSort() + " " + select.getOrder());
		}
		return hql;
	}

	/**
	 * 添加过滤条件
	 * 
	 * @param select
	 *            VO
	 * @param hql
	 *            hql
	 * @param params
	 *            hql
	 * @return 拼装之后 hql
	 */
	private StringBuffer addWhere(Select select, StringBuffer hql, Map<String, Object> params) {
		if (select.getSelectName() != null && !select.getSelectName().trim().equals("")) {
			hql.append(" AND p.selectName LIKE :selectName");
			params.put("selectName", "%%" + select.getSelectName().trim() + "%%");
		}
		return hql;
	}

	@Override
	public void remove(String ids) {
		String[] nids = ids.split(",");
		StringBuffer hql = new StringBuffer("DELETE Pselect p WHERE p.id IN (");
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql.append(",");
			}
			hql.append("'" + nids[i] + "'");
		}
		hql.append(")");
		selectDao.executeHql(hql.toString());
	}

	@Override
	public void approveOk(Select select) {
		String ids = select.getIds();
		String[] nids = ids.split(",");
		StringBuffer hql = new StringBuffer("UPDATE Pselect p SET p.selectApprove = '");
		hql.append(select.getUsername() + "' WHERE p.id IN (");
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql.append(",");
			}
			hql.append("'" + nids[i] + "'");
		}
		hql.append(")");
		selectDao.executeHql(hql.toString());
	}

	@Override
	public DataGrid searchForSubmit(Select select) {
		//
		DataGrid dg = new DataGrid();
		StringBuffer hql = new StringBuffer("FROM Pselect p WHERE 1=1");
		hql.append(" AND p.selectApprove IS NOT NULL ")//
				.append(" AND p.selectCount IS NULL ")//
				.append(" AND p.selectVender IS NULL ");
		Map<String, Object> params = new HashMap<String, Object>();

		hql = addWhere(select, hql, params);
		StringBuffer totalHql = new StringBuffer("SELECT COUNT(*) " + hql);
		hql = addOrder(select, hql);
		// System.out.println(hql);
		List<Pselect> l = selectDao.find(hql.toString(), params, select.getPage(), select.getRows());
		List<Select> nl = new ArrayList<Select>();
		changeModel(l, nl);
		dg.setTotal(selectDao.count(totalHql.toString(), params));
		dg.setRows(nl);
		return dg;
	}

	@Override
	public void submitList(Select select) {
		String ids = select.getIds();
		String[] nids = ids.split(",");
		StringBuffer hql = new StringBuffer("UPDATE Pselect p SET p.selectVender='");
		hql.append(select.getSelectVender() + "', p.selectCount=");
		hql.append(select.getSelectCount() + "  WHERE p.id IN (");
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql.append(",");
			}
			hql.append("'" + nids[i] + "'");
		}
		hql.append(")");
		selectDao.executeHql(hql.toString());
	}

	@Override
	public DataGrid searchForSign(Select select) {
		//
		DataGrid dg = new DataGrid();
		StringBuffer hql = new StringBuffer("FROM Pselect p WHERE 1=1 ");
		hql.append(" AND p.selectApprove IS NOT NULL ")//
				.append(" AND p.selectStat IS NULL")//
				.append(" AND p.selectVender IS NOT NULL ")//
				.append(" AND p.selectCount > 0 ");
		Map<String, Object> params = new HashMap<String, Object>();

		hql = addWhere(select, hql, params);
		StringBuffer totalHql = new StringBuffer("SELECT COUNT(*) " + hql);
		hql = addOrder(select, hql);
		// System.out.println(hql);
		List<Pselect> l = selectDao.find(hql.toString(), params, select.getPage(), select.getRows());
		List<Select> nl = new ArrayList<Select>();
		changeModel(l, nl);
		dg.setTotal(selectDao.count(totalHql.toString(), params));
		dg.setRows(nl);
		return dg;
	}

	@Override
	public void dealsign(Select select) {
		String ids = select.getIds();
		String[] nids = ids.split(",");
		// 先进行置空
		StringBuffer phql = new StringBuffer("UPDATE Pselect p SET p.selectStat=NULL WHERE p.id IN(");
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				phql.append(",");
			}
			phql.append("'" + nids[i] + "'");
		}
		phql.append(")");
		selectDao.executeHql(phql.toString());

		// 更新
		StringBuffer hql = new StringBuffer("UPDATE Pselect p SET p.selectStat='");
		hql.append(select.getUsername() + ":");
		hql.append(select.getSelectStat());
		hql.append("'  WHERE p.id IN (");
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql.append(",");
			}
			hql.append("'" + nids[i] + "'");
		}
		hql.append(")");
		selectDao.executeHql(hql.toString());
	}

	@Override
	public DataGrid searchForCheck(Select select) {
		//
		DataGrid dg = new DataGrid();
		StringBuffer hql = new StringBuffer("FROM Pselect p WHERE 1=1 ");//
		hql.append(" AND p.selectApprove IS NOT NULL")//
				.append(" AND p.selectVender IS NOT NULL ")//
				.append(" AND p.selectCount > 0 ")//
				.append(" AND p.selectStat IS NOT NULL ")//
				.append(" AND p.selectAccept IS NULL ");
		Map<String, Object> params = new HashMap<String, Object>();

		hql = addWhere(select, hql, params);
		StringBuffer totalHql = new StringBuffer("SELECT COUNT(*) " + hql);
		hql = addOrder(select, hql);
		// System.out.println(hql);
		List<Pselect> l = selectDao.find(hql.toString(), params, select.getPage(), select.getRows());
		List<Select> nl = new ArrayList<Select>();
		changeModel(l, nl);
		dg.setTotal(selectDao.count(totalHql.toString(), params));
		dg.setRows(nl);
		return dg;
	}

	@Override
	public void equipCheck(Select select) {
		String ids = select.getIds();
		String[] nids = ids.split(",");
		// 先进行置空
		StringBuffer phql = new StringBuffer("UPDATE Pselect p SET p.selectAccept=NULL WHERE p.id IN(");
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				phql.append(",");
			}
			phql.append("'" + nids[i] + "'");
		}
		phql.append(")");
		selectDao.executeHql(phql.toString());

		// 更新
		StringBuffer hql = new StringBuffer("UPDATE Pselect p SET p.selectAccept='");
		hql.append(select.getUsername());
		hql.append("'  WHERE p.id IN (");
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql.append(",");
			}
			hql.append("'" + nids[i] + "'");
		}
		hql.append(")");
		selectDao.executeHql(hql.toString());

		// 自动生成Peq
		StringBuffer eqhql = new StringBuffer("FROM Pselect p WHERE p.id IN(");
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				eqhql.append(",");
			}
			eqhql.append("'" + nids[i] + "'");
		}
		eqhql.append(")");
		List<Pselect> selectList = selectDao.find(eqhql.toString());
		for (Pselect pselect : selectList) {
			add(pselect);
		}
	}

	private void add(Pselect select) {
		//
		Peq p = new Peq();

		try {
			p.setEqName(select.getSelectName());
			p.setEqParam(select.getSelectParam());
			p.setEqType(select.getSelectType());
			p.setEqVender(select.getSelectVender());
			p.setEqInfo(select.getSelectInfo());
			p.setEqYear(select.getSelectYear());
			p.setEqPact(select.getSelectStat());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		eqDao.save(p);
	}

	@Override
	public DataGrid searchForPurchase(Select select) {
		//
		DataGrid dg = new DataGrid();
		StringBuffer hql = new StringBuffer("FROM Pselect p WHERE 1=1 ");
		hql.append(" AND p.selectApprove IS NOT NULL")//
				.append(" AND p.selectVender IS NOT NULL ")//
				.append(" AND p.selectCount > 0")//
				.append(" AND p.selectStat IS NOT NULL ")//
				.append(" AND p.selectAccept IS NOT NULL ");
		Map<String, Object> params = new HashMap<String, Object>();

		hql = addWhere(select, hql, params);
		StringBuffer totalHql = new StringBuffer("SELECT COUNT(*) " + hql);
		hql = addOrder(select, hql);
		// System.out.println(hql);
		List<Pselect> l = selectDao.find(hql.toString(), params, select.getPage(), select.getRows());
		List<Select> nl = new ArrayList<Select>();
		changeModel(l, nl);
		dg.setTotal(selectDao.count(totalHql.toString(), params));
		dg.setRows(nl);
		return dg;
	}

}
