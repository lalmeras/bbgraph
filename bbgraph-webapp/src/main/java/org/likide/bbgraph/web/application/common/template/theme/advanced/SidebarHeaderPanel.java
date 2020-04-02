package org.likide.bbgraph.web.application.common.template.theme.advanced;

import org.apache.wicket.markup.html.panel.Panel;
import org.likide.bbgraph.web.application.BbgraphApplication;
import org.iglooproject.wicket.more.condition.Condition;

public class SidebarHeaderPanel extends Panel {

	private static final long serialVersionUID = -926306336711136387L;

	public SidebarHeaderPanel(String id) {
		super(id);
		
		add(Condition.anyChildVisible(this).thenShow());
		
		add(
			BbgraphApplication.get().getHomePageLinkDescriptor()
				.link("home")
		);
	}

}
