package org.likide.bbgraph.core.util.monitoring;

import org.likide.bbgraph.core.business.user.service.IUserService;
import org.iglooproject.jpa.exception.SecurityServiceException;
import org.iglooproject.jpa.exception.ServiceException;
import org.iglooproject.jpa.util.IDatabaseConsistencyCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("databaseConsistencyCheckService")
public class DatabaseConsistencyCheckServiceImpl implements IDatabaseConsistencyCheckService {

	@Autowired
	private IUserService userService;

	@Override
	public void checkDatabaseAccess() {
		userService.count();
	}

	@Override
	public void checkDatabaseConsistency() throws ServiceException, SecurityServiceException {
		// rien
	}

	@Override
	public void checkOnStartup(boolean allowCreateMissingElements) throws ServiceException, SecurityServiceException {
		// rien
	}

}
