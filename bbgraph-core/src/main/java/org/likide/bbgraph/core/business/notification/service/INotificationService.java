package org.likide.bbgraph.core.business.notification.service;

import org.iglooproject.jpa.exception.ServiceException;
import org.likide.bbgraph.core.business.user.model.User;

public interface INotificationService {

	void sendExampleNotification(User user) throws ServiceException;

	/**
	 * Sends a mail to user from a specified contact
	 * @param userTo : recipient user
	 * @param from : "from" mail address
	 * @throws ServiceException
	 */
	void sendExampleNotification(User userTo, String from) throws ServiceException;

	void sendUserPasswordRecoveryRequest(User user) throws ServiceException;

}
