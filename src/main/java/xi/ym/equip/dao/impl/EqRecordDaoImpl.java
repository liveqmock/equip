package xi.ym.equip.dao.impl;

import org.springframework.stereotype.Repository;

import xi.ym.equip.dao.EqRecordDaoI;
import xi.ym.equip.domain.PeqRecord;

@Repository("eqRecordDao")
public class EqRecordDaoImpl extends BaseDaoImpl<PeqRecord> implements EqRecordDaoI{

}
