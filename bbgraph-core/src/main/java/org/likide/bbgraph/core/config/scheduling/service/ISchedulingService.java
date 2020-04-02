package org.likide.bbgraph.core.config.scheduling.service;

import org.iglooproject.jpa.business.generic.service.ITransactionalAspectAwareService;

public interface ISchedulingService extends ITransactionalAspectAwareService {

	void temporaryFilesCleaning();

}
