package org.likide.bbgraph.core.business.history.search;

import java.util.Date;
import java.util.Set;

import org.likide.bbgraph.core.business.history.model.HistoryLog;
import org.likide.bbgraph.core.business.history.model.atomic.HistoryEventType;
import org.likide.bbgraph.core.business.user.model.User;
import org.iglooproject.jpa.business.generic.model.GenericEntity;
import org.iglooproject.jpa.more.business.history.search.HistoryLogSort;
import org.iglooproject.jpa.more.business.search.query.ISearchQuery;

public interface IHistoryLogSearchQuery extends ISearchQuery<HistoryLog, HistoryLogSort> {

	IHistoryLogSearchQuery subject(User user);

	IHistoryLogSearchQuery date(Date dateMin, Date dateMax);

	IHistoryLogSearchQuery object(GenericEntity<?, ?> object);

	IHistoryLogSearchQuery object1(GenericEntity<?, ?> object);

	IHistoryLogSearchQuery object2(GenericEntity<?, ?> object);

	IHistoryLogSearchQuery object3(GenericEntity<?, ?> object);

	IHistoryLogSearchQuery object4(GenericEntity<?, ?> object);

	IHistoryLogSearchQuery differencesMandatoryFor(Set<HistoryEventType> mandatoryDifferencesEventTypes);

}
