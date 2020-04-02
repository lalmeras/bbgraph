package org.likide.bbgraph.core.business.referencedata.dao;

import org.likide.bbgraph.core.business.common.model.PostalCode;
import org.likide.bbgraph.core.business.referencedata.model.City;
import org.iglooproject.jpa.business.generic.dao.IGenericEntityDao;

public interface ICityDao extends IGenericEntityDao<Long, City>{

	City getByLabelAndPostalCode(String label, PostalCode postalCode);

}
