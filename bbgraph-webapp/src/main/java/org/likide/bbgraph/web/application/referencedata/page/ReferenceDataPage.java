package org.likide.bbgraph.web.application.referencedata.page;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.iglooproject.wicket.markup.html.basic.CoreLabel;
import org.iglooproject.wicket.more.link.descriptor.IPageLinkDescriptor;
import org.iglooproject.wicket.more.link.descriptor.builder.LinkDescriptorBuilder;
import org.likide.bbgraph.web.application.common.component.FeatureNotYetAvailablePanel;
import org.likide.bbgraph.web.application.common.component.NavTabsPanel;
import org.likide.bbgraph.web.application.referencedata.template.ReferenceDataTemplate;

public class ReferenceDataPage extends ReferenceDataTemplate {

	private static final long serialVersionUID = -4381694964311714573L;

	public static final IPageLinkDescriptor linkDescriptor() {
		return LinkDescriptorBuilder.start()
			.page(ReferenceDataPage.class);
	}

	public ReferenceDataPage(PageParameters parameters) {
		super(parameters);
		
		add(new CoreLabel("pageTitle", new ResourceModel("navigation.referenceData")));
		
		add(
			new NavTabsPanel("tabs")
				.add(
					new NavTabsPanel.SimpleTabFactory(Model.of("Reference data #2")) {
						private static final long serialVersionUID = 1L;
						@Override
						public Component createContent(String wicketId) {
							return new FeatureNotYetAvailablePanel(wicketId);
						}
					}
				)
				.add(
					new NavTabsPanel.SimpleTabFactory(Model.of("Reference data #3")) {
						private static final long serialVersionUID = 1L;
						@Override
						public Component createContent(String wicketId) {
							return new FeatureNotYetAvailablePanel(wicketId);
						}
					}
				)
				.add(
					new NavTabsPanel.SimpleTabFactory(Model.of("Reference data #4")) {
						private static final long serialVersionUID = 1L;
						@Override
						public Component createContent(String wicketId) {
							return new FeatureNotYetAvailablePanel(wicketId);
						}
					}
				)
				.add(
					new NavTabsPanel.SimpleTabFactory(Model.of("Reference data #5")) {
						private static final long serialVersionUID = 1L;
						@Override
						public Component createContent(String wicketId) {
							return new FeatureNotYetAvailablePanel(wicketId);
						}
					}
				)
		);
	}

}
