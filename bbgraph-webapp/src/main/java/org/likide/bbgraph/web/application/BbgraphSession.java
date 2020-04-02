package org.likide.bbgraph.web.application;

import static org.likide.bbgraph.core.property.BbgraphCorePropertyIds.ENVIRONMENT;

import org.apache.wicket.Session;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.Request;
import org.likide.bbgraph.core.business.user.model.User;
import org.likide.bbgraph.core.config.util.Environment;
import org.iglooproject.wicket.more.AbstractCoreSession;
import org.iglooproject.wicket.more.model.ApplicationPropertyModel;

public class BbgraphSession extends AbstractCoreSession<User> {

	private static final long serialVersionUID = 1870827020904365541L;

	private final IModel<Environment> environmentModel = ApplicationPropertyModel.of(ENVIRONMENT);

	public BbgraphSession(Request request) {
		super(request);
	}

	public static BbgraphSession get() {
		return (BbgraphSession) Session.get();
	}

	public IModel<Environment> getEnvironmentModel() {
		return environmentModel;
	}

}