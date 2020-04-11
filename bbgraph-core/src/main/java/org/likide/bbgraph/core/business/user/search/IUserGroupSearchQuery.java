package org.likide.bbgraph.core.business.user.search;

import org.iglooproject.jpa.more.business.search.query.ISearchQuery;
import org.likide.bbgraph.core.business.user.model.User;
import org.likide.bbgraph.core.business.user.model.UserGroup;

public interface IUserGroupSearchQuery extends ISearchQuery<UserGroup, UserGroupSort> {

	IUserGroupSearchQuery user(User user);

	IUserGroupSearchQuery name(String name);

}
