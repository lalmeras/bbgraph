package org.likide.bbgraph.web.application.common.template.theme.advanced;

import org.apache.wicket.markup.html.panel.Panel;
import org.likide.bbgraph.web.application.BbgraphApplication;

public class SidebarNavbarPanel extends Panel {

	private static final long serialVersionUID = -3741272240940846720L;

	public SidebarNavbarPanel(String id) {
		super(id);
		
		add(
			BbgraphApplication.get().getHomePageLinkDescriptor()
				.link("home")
		);
	}

}
