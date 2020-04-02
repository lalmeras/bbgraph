package org.likide.bbgraph.web.application.security.password.page;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.likide.bbgraph.web.application.BbgraphSession;
import org.likide.bbgraph.web.application.security.password.component.SecurityPasswordRecoveryContentPanel;
import org.likide.bbgraph.web.application.security.password.template.SecurityPasswordTemplate;
import org.iglooproject.wicket.more.link.descriptor.IPageLinkDescriptor;
import org.iglooproject.wicket.more.link.descriptor.builder.LinkDescriptorBuilder;
import org.iglooproject.wicket.more.markup.html.template.model.BreadCrumbElement;

public class SecurityPasswordRecoveryPage extends SecurityPasswordTemplate {

	private static final long serialVersionUID = 547223775134254240L;

	public static final IPageLinkDescriptor linkDescriptor() {
		return LinkDescriptorBuilder.start()
			.page(SecurityPasswordRecoveryPage.class);
	}

	public SecurityPasswordRecoveryPage(PageParameters parameters) {
		super(parameters);
		
		// Being connected here doesn't make any sense
		if (BbgraphSession.get().isSignedIn()) {
			BbgraphSession.get().invalidate();
		}
		
		addHeadPageTitlePrependedElement(new BreadCrumbElement(
			new ResourceModel("security.password.recovery.pageTitle")
		));
	}

	@Override
	protected IModel<String> getTitleModel() {
		return new ResourceModel("security.password.recovery.pageTitle");
	}

	@Override
	protected Component getContentComponent(String wicketId) {
		return new SecurityPasswordRecoveryContentPanel(wicketId);
	}

}
