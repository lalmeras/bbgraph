package org.likide.bbgraph.core.business.notification.service;

import org.likide.bbgraph.core.business.user.model.User;

public class EmptyNotificationUrlBuilderServiceImpl implements INotificationUserProfileUrlBuilderService {

	@Override
	public String getUserDescriptionUrl(User user) {
		return null;
	}

}
