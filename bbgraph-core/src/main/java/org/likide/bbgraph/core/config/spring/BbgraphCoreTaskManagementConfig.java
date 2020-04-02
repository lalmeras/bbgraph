package org.likide.bbgraph.core.config.spring;

import java.util.Collection;

import org.apache.commons.lang3.EnumUtils;
import org.likide.bbgraph.core.business.task.model.BbgraphTaskQueueId;
import org.iglooproject.jpa.more.business.task.model.IQueueId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BbgraphCoreTaskManagementConfig {

	@Bean
	public Collection<? extends IQueueId> queueIds() {
		return EnumUtils.getEnumList(BbgraphTaskQueueId.class);
	}

}
