package org.likide.bbgraph.web.application.administration.component.tab;

import org.apache.wicket.model.IModel;
import org.likide.bbgraph.core.business.user.model.BasicUser;
import org.likide.bbgraph.web.application.administration.component.BasicUserDetailDescriptionPanel;
import org.likide.bbgraph.web.application.administration.component.BasicUserDetailGroupsPanel;
import org.iglooproject.wicket.markup.html.panel.GenericPanel;

public class AdministrationBasicUserDetailTabMainInformationPanel extends GenericPanel<BasicUser> {

	private static final long serialVersionUID = -3900528127687137340L;

	public AdministrationBasicUserDetailTabMainInformationPanel(String id, final IModel<? extends BasicUser> userModel) {
		super(id, userModel);
		
		add(
			new BasicUserDetailDescriptionPanel("description", userModel),
			new BasicUserDetailGroupsPanel("groups", userModel)
		);
	}

}
