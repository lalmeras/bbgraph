package org.likide.bbgraph.core.business.user.dao;

import org.springframework.stereotype.Repository;

import org.likide.bbgraph.core.business.user.model.User;
import org.likide.bbgraph.core.business.user.model.UserGroup;
import org.iglooproject.jpa.security.business.person.dao.GenericUserGroupDaoImpl;

@Repository("personGroupDao")
public class UserGroupDaoImpl extends GenericUserGroupDaoImpl<UserGroup, User> implements IUserGroupDao {

	public UserGroupDaoImpl() {
		super();
	}

}
