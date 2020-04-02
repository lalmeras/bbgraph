package org.likide.bbgraph.core.business.user.search;

import org.likide.bbgraph.core.business.user.model.BasicUser;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
public interface IBasicUserSearchQuery extends IAbstractUserSearchQuery<BasicUser> {

}
