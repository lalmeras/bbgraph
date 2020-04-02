package org.likide.bbgraph.web.application.common.template.theme.common;

import org.apache.wicket.model.Model;
import org.likide.bbgraph.core.config.util.Environment;
import org.likide.bbgraph.web.application.BbgraphSession;
import org.iglooproject.wicket.more.condition.Condition;
import org.iglooproject.wicket.more.markup.html.basic.EnclosureContainer;

public class BootstrapBreakpointPanel extends EnclosureContainer {

	private static final long serialVersionUID = 5271828582493462504L;

	public BootstrapBreakpointPanel(String id) {
		super(id);
		setOutputMarkupId(true);
		
		condition(Condition.isEqual(BbgraphSession.get().getEnvironmentModel(), Model.of(Environment.development)));
	}

}
