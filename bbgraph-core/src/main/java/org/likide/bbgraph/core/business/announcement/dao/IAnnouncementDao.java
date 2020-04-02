package org.likide.bbgraph.core.business.announcement.dao;

import java.util.Date;
import java.util.List;

import org.likide.bbgraph.core.business.announcement.model.Announcement;
import org.iglooproject.jpa.business.generic.dao.IGenericEntityDao;

public interface IAnnouncementDao extends IGenericEntityDao<Long, Announcement> {

	List<Announcement> listActive();

	Date getMostRecentPublicationStartDate();

}
