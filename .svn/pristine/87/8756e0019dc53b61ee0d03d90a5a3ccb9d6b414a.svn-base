package xi.ym.equip.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import xi.ym.equip.dao.StorageDaoI;
import xi.ym.equip.domain.Pstorage;
import xi.ym.equip.pageModel.DataGrid;
import xi.ym.equip.pageModel.Storage;
import xi.ym.equip.service.StorageServiceI;

@Service("storageService")
public class StorageServiceImpl implements StorageServiceI {
	@Resource
	private StorageDaoI storageDao;

	/**
	 * 装换PO<--->VO
	 * 
	 * @param l
	 * @param nl
	 */
	private void changeModel(List<Pstorage> l, List<Storage> nl) {
		if (l != null && l.size() > 0) {
			for (Pstorage p : l) {
				Storage s = new Storage();
				BeanUtils.copyProperties(p, s, new String[] { "eqNumb", "storageNumb" });
				try {
					s.setEqNumb(String.valueOf(p.getEqNumb()));
					s.setStorageNumb(String.valueOf(p.getStorageNumb()));
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
	 * @param storage
	 *            VO
	 * @param hql
	 *            传入hql
	 * @return 拼装之后的hql
	 */
	private StringBuffer addOrder(Storage storage, StringBuffer hql) {
		if (storage.getSort() != null) {
			hql.append(" ORDER BY " + storage.getSort() + " " + storage.getOrder());
		}
		return hql;
	}

	/**
	 * 添加过滤条件
	 * 
	 * @param storage
	 *            VO
	 * @param hql
	 *            hql
	 * @param params
	 *            hql
	 * @return 拼装之后 hql
	 */
	private StringBuffer addWhere(Storage storage, StringBuffer hql, Map<String, Object> params) {

		if (storage.getStorageNumb() != null && !storage.getStorageNumb().trim().equals("")) {
			hql.append(" AND p.storageNumb=:storageNumb");
			params.put("storageNumb", Integer.parseInt(storage.getStorageNumb()));
		}
		return hql;
	}

	@Override
	public void inputData(String ids) {
		//
		String[] nids = ids.split(",");
		for (String id : nids) {
			if (Integer.parseInt(id) == 0)
				return;
		}
		StringBuffer hql = new StringBuffer("UPDATE Pstorage p  SET p.storageNumb = 0 WHERE p.id IN (");
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql.append(",");
			}
			hql.append("'" + nids[i] + "'");
		}
		hql.append(")");
		storageDao.executeHql(hql.toString());
	}

	@Override
	public void outputData(String ids, String storageNumb) {
		//
		String[] nids = ids.split(",");
		StringBuffer hql = new StringBuffer("UPDATE Pstorage p SET p.storageNumb = ");
		hql.append(storageNumb + " WHERE p.id IN (");
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql.append(",");
			}
			hql.append("'" + nids[i] + "'");
		}
		hql.append(")");
		storageDao.executeHql(hql.toString());
	}

	@Override
	public DataGrid inList(Storage storage) {
		//
		DataGrid dg = new DataGrid();
		StringBuffer hql = new StringBuffer("FROM Pstorage p ");
		Map<String, Object> params = new HashMap<String, Object>();
		// 机房号为 0 表示没有出库
		hql.append(" WHERE p.storageNumb=:storageNumb1");
		params.put("storageNumb1", 0);

		hql = addWhere(storage, hql, params);

		StringBuffer totalHql = new StringBuffer("SELECT COUNT(*) " + hql);
		hql = addOrder(storage, hql);
		// System.out.println(hql);
		List<Pstorage> l = storageDao.find(hql.toString(), params, storage.getPage(), storage.getRows());
		List<Storage> nl = new ArrayList<Storage>();
		changeModel(l, nl);
		dg.setTotal(storageDao.count(totalHql.toString(), params));
		dg.setRows(nl);
		return dg;
	}

	@Override
	public DataGrid outList(Storage storage) {
		//
		DataGrid dg = new DataGrid();
		StringBuffer hql = new StringBuffer("FROM Pstorage p WHERE 1=1");
		Map<String, Object> params = new HashMap<String, Object>();

		// 机房号不为0表示已经出库
		hql.append(" AND p.storageNumb!=:storageNumb1");
		params.put("storageNumb1", 0);

		hql = addWhere(storage, hql, params);
		StringBuffer totalHql = new StringBuffer("SELECT COUNT(*) " + hql);
		hql = addOrder(storage, hql);
		// System.out.println(hql);
		List<Pstorage> l = storageDao.find(hql.toString(), params, storage.getPage(), storage.getRows());
		List<Storage> nl = new ArrayList<Storage>();
		changeModel(l, nl);
		dg.setTotal(storageDao.count(totalHql.toString(), params));
		dg.setRows(nl);
		return dg;
	}
}
