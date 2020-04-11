package org.likide.bbgraph.core.business.notification.service;

import java.util.Date;

import org.iglooproject.spring.notification.model.INotificationContentDescriptor;
import org.likide.bbgraph.core.business.user.model.User;

public interface IBbgraphNotificationContentDescriptorFactory {

	INotificationContentDescriptor example(User user, Date date);

	INotificationContentDescriptor userPasswordRecoveryRequest(User user);

}
