package org.likide.bbgraph.core.business.user.search;

import org.likide.bbgraph.core.business.user.model.User;
import org.likide.bbgraph.core.business.user.model.UserGroup;
import org.likide.bbgraph.core.util.binding.Bindings;
import org.iglooproject.jpa.more.business.search.query.AbstractHibernateSearchSearchQuery;

public abstract class AbstractUserSearchQueryImpl<U extends User> extends AbstractHibernateSearchSearchQuery<U, UserSort>
		implements IAbstractUserSearchQuery<U> /* NOT Serializable */ {

	protected AbstractUserSearchQueryImpl(Class<? extends U> clazz) {
		super(clazz);
	}

	@Override
	public IAbstractUserSearchQuery<U> name(String name) {
		must(matchFuzzyIfGiven(name, 2, Bindings.user().firstName(), Bindings.user().lastName(), Bindings.user().username()));
		return this;
	}

	@Override
	public IAbstractUserSearchQuery<U> group(UserGroup group) {
		must(matchIfGiven(Bindings.user().groups().getPath(), group));
		return this;
	}

	@Override
	public IAbstractUserSearchQuery<U> includeInactive(Boolean includeInactives) {
		must(matchIfTrue(Bindings.user().active(), Boolean.TRUE, !includeInactives));
		return this;
	}

	@Override
	public IAbstractUserSearchQuery<U> nameAutocomplete(String terms) {
		must(matchAutocompleteIfGiven(terms, Bindings.user().firstName(), Bindings.user().lastName(), Bindings.user().username()));
		return this;
	}

}