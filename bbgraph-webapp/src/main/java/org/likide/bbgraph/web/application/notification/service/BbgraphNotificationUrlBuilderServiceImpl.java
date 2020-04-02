package org.likide.bbgraph.web.application.notification.service;

import java.util.concurrent.Callable;

import org.likide.bbgraph.core.business.notification.service.INotificationUserProfileUrlBuilderService;
import org.likide.bbgraph.core.business.user.model.User;
import org.likide.bbgraph.web.application.administration.template.AdministrationUserDetailTemplate;
import org.iglooproject.wicket.more.link.descriptor.generator.IPageLinkGenerator;
import org.iglooproject.wicket.more.model.GenericEntityModel;
import org.iglooproject.wicket.more.notification.service.AbstractNotificationUrlBuilderServiceImpl;
import org.iglooproject.wicket.more.notification.service.IWicketContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This service is used to generate the URL used in the text version of the notification emails.
 * 
 * It shouldn't be used for other purposes.
 */
@Service("bbgraphNotificationUrlBuilderService")
public class BbgraphNotificationUrlBuilderServiceImpl extends AbstractNotificationUrlBuilderServiceImpl
		implements INotificationUserProfileUrlBuilderService {

	@Autowired
	public BbgraphNotificationUrlBuilderServiceImpl(IWicketContextProvider contextProvider) {
		super(contextProvider);
	}

	@Override
	public String getUserDescriptionUrl(final User user) {
		Callable<IPageLinkGenerator> pageLinkGeneratorTask = new Callable<IPageLinkGenerator>() {
			@Override
			public IPageLinkGenerator call() {
				return AdministrationUserDetailTemplate.mapper().ignoreParameter2().map(GenericEntityModel.of(user));
			}
		};
		
		return buildUrl(pageLinkGeneratorTask);
	}
}
