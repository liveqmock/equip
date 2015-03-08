package xi.ym.equip.dao.impl;

import org.springframework.stereotype.Repository;

import xi.ym.equip.dao.UserDaoI;
import xi.ym.equip.domain.Puser;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<Puser> implements UserDaoI {

}
