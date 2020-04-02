package org.likide.bbgraph.core.business.user.dao;

import org.likide.bbgraph.core.business.user.model.User;
import org.likide.bbgraph.core.business.user.model.UserGroup;
import org.iglooproject.jpa.security.business.person.dao.IGenericUserGroupDao;

public interface IUserGroupDao extends IGenericUserGroupDao<UserGroup, User> {

}
