package org.likide.bbgraph.core.security.service;

import org.likide.bbgraph.core.business.referencedata.model.ReferenceData;
import org.likide.bbgraph.core.business.user.model.User;
import org.iglooproject.jpa.security.service.IGenericPermissionEvaluator;

public interface IReferenceDataPermissionEvaluator extends IGenericPermissionEvaluator<User, ReferenceData<?>> {

}
