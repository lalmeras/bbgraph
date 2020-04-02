package org.likide.bbgraph.web.application.util.binding;

import org.iglooproject.wicket.more.model.IBindableDataProviderBinding;


public final class WebappBindings {

	private static final IBindableDataProviderBinding IBINDABLE_DATA_PROVIDER = new IBindableDataProviderBinding();

	public static IBindableDataProviderBinding iBindableDataProvider() {
		return IBINDABLE_DATA_PROVIDER;
	}

	private WebappBindings() {
	}

}