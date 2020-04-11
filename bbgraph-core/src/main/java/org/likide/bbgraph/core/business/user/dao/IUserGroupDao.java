package org.likide.bbgraph.core.business.user.dao;

import org.iglooproject.jpa.security.business.person.dao.IGenericUserGroupDao;
import org.likide.bbgraph.core.business.user.model.User;
import org.likide.bbgraph.core.business.user.model.UserGroup;

public interface IUserGroupDao extends IGenericUserGroupDao<UserGroup, User> {

}
