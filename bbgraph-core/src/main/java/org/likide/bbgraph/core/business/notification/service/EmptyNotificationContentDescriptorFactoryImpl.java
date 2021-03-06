package org.likide.bbgraph.core.business.notification.service;

import java.util.Date;

import org.iglooproject.spring.notification.model.INotificationContentDescriptor;
import org.iglooproject.spring.notification.util.NotificationContentDescriptors;
import org.likide.bbgraph.core.business.user.model.User;

public class EmptyNotificationContentDescriptorFactoryImpl implements IBbgraphNotificationContentDescriptorFactory {
	
	private static final INotificationContentDescriptor DEFAULT_DESCRIPTOR =
		NotificationContentDescriptors.explicit("defaultSubject", "defaultTextBody", "defaultHtmlBody");

	@Override
	public INotificationContentDescriptor example(User user, Date date) {
		return DEFAULT_DESCRIPTOR;
	}

	@Override
	public INotificationContentDescriptor userPasswordRecoveryRequest(User user) {
		return DEFAULT_DESCRIPTOR;
	}

}
