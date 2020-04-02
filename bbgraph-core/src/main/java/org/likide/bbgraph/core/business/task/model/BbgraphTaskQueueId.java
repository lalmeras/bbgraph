package org.likide.bbgraph.core.business.task.model;

import org.iglooproject.jpa.more.business.task.model.IQueueId;

public enum BbgraphTaskQueueId implements IQueueId {

	// Define queue IDs here.
	; 

	@Override
	public String getUniqueStringId() {
		return name();
	}

}
