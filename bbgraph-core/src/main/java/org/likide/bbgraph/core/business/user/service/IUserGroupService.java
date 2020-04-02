package org.likide.bbgraph.core.business.user.service;

import org.likide.bbgraph.core.business.user.model.User;
import org.likide.bbgraph.core.business.user.model.UserGroup;
import org.iglooproject.jpa.security.business.person.service.IGenericUserGroupService;

public interface IUserGroupService extends IGenericUserGroupService<UserGroup, User> {

}
