package org.likide.bbgraph.web.application.administration.component.tab;

import org.apache.wicket.model.IModel;
import org.likide.bbgraph.core.business.user.model.TechnicalUser;
import org.likide.bbgraph.web.application.administration.component.TechnicalUserDetailDescriptionPanel;
import org.likide.bbgraph.web.application.administration.component.TechnicalUserDetailGroupsPanel;
import org.iglooproject.wicket.markup.html.panel.GenericPanel;

public class AdministrationTechnicalUserDetailTabMainInformationPanel extends GenericPanel<TechnicalUser> {

	private static final long serialVersionUID = -6822072984820432812L;

	public AdministrationTechnicalUserDetailTabMainInformationPanel(String id, final IModel<? extends TechnicalUser> userModel) {
		super(id, userModel);
		
		add(
			new TechnicalUserDetailDescriptionPanel("description", userModel),
			new TechnicalUserDetailGroupsPanel("groups", userModel)
		);
	}

}
