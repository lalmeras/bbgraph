package org.likide.bbgraph.core.config.spring;

import org.likide.bbgraph.core.config.scheduling.service.ISchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class BbgraphCoreSchedulingConfig {

	@Autowired
	private ISchedulingService schedulingService;

	@Scheduled(cron = "${tmp.clean.cron}")
	public void temporaryFilesCleaning() {
		schedulingService.temporaryFilesCleaning();
	}

}
