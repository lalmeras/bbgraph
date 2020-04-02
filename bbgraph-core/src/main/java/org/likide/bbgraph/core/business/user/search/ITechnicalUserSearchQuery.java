package org.likide.bbgraph.core.business.user.search;

import org.likide.bbgraph.core.business.user.model.TechnicalUser;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
public interface ITechnicalUserSearchQuery extends IAbstractUserSearchQuery<TechnicalUser> {

}
