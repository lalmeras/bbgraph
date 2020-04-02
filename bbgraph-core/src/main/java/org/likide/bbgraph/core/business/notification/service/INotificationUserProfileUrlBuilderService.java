package org.likide.bbgraph.core.business.notification.service;

import org.likide.bbgraph.core.business.user.model.User;
import org.iglooproject.spring.notification.service.INotificationUrlBuilderService;

public interface INotificationUserProfileUrlBuilderService extends INotificationUrlBuilderService {

	String getUserDescriptionUrl(User user);

}
