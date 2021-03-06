package org.likide.bbgraph.core.business.notification.service;

import java.util.Date;
import java.util.Locale;

import org.iglooproject.jpa.exception.ServiceException;
import org.iglooproject.spring.notification.model.SimpleRecipient;
import org.iglooproject.spring.notification.service.AbstractNotificationServiceImpl;
import org.likide.bbgraph.core.business.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("notificationService")
public class NotificationServiceImpl extends AbstractNotificationServiceImpl implements INotificationService {

	private static final String ERROR_EXCEPTION_MESSAGE = "Error during send mail process (to: %s, subject: %s)";

	@Autowired
	private INotificationUserProfileUrlBuilderService notificationUrlBuilderService;

	@Autowired
	private IBbgraphNotificationContentDescriptorFactory contentDescriptorFactory;

	@Override
	public void sendExampleNotification(User user) throws ServiceException {
		Date date = new Date();
		String url = notificationUrlBuilderService.getUserDescriptionUrl(user);
		
		try {
			builder().to(user)
				.content(contentDescriptorFactory.example(user, date))
				.template("example.ftl")
				.variable("userFullName", user.getFullName())
				.variable("date", date)
				.variable("url", url)
				.send();
		} catch (RuntimeException | ServiceException e) {
			throw new ServiceException(ERROR_EXCEPTION_MESSAGE, e);
		}
	}
	
	@Override
	public void sendUserPasswordRecoveryRequest(User user) throws ServiceException {
		try {
			builder()
				.to(user)
				.content(contentDescriptorFactory.userPasswordRecoveryRequest(user))
				.send();
		} catch (RuntimeException | ServiceException e) {
			throw new ServiceException(ERROR_EXCEPTION_MESSAGE, e);
		}
	}

	@Override
	public void sendExampleNotification(User userTo, String from) throws ServiceException {
		try {
			Date date = new Date();
			builder()
				.sender("no-reply@basicapp.org")
				.from(from)
				.to(new SimpleRecipient(Locale.FRANCE, userTo.getEmail(), userTo.getDisplayName()))
				.content(contentDescriptorFactory.example(userTo, date))
				.send();
		} catch (RuntimeException | ServiceException e) {
			throw new ServiceException(ERROR_EXCEPTION_MESSAGE, e);
		}
	}

}
