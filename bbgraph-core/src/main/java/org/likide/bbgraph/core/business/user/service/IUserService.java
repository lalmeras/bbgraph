package org.likide.bbgraph.core.business.user.service;

import java.util.List;

import org.iglooproject.jpa.exception.SecurityServiceException;
import org.iglooproject.jpa.exception.ServiceException;
import org.iglooproject.jpa.security.business.person.service.IGenericUserService;
import org.likide.bbgraph.core.business.user.model.User;

public interface IUserService extends IGenericUserService<User> {

	List<User> listByUsername(String username);

	void onSignIn(User user) throws ServiceException, SecurityServiceException;

	void onSignInFail(User user) throws ServiceException, SecurityServiceException;

	void onCreate(User user, User subject) throws ServiceException, SecurityServiceException;

	User getByEmailCaseInsensitive(String email);

	User getAuthenticatedUser();

}