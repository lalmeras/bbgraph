package org.likide.bbgraph.core.business.notification.service;

import org.iglooproject.spring.notification.service.INotificationUrlBuilderService;
import org.likide.bbgraph.core.business.user.model.User;

public interface INotificationUserProfileUrlBuilderService extends INotificationUrlBuilderService {

	String getUserDescriptionUrl(User user);

}
