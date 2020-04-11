package org.likide.bbgraph.core.security.service;

import java.util.Arrays;
import java.util.Collection;

import org.iglooproject.jpa.business.generic.model.GenericEntity;
import org.iglooproject.jpa.security.service.IGenericPermissionEvaluator;
import org.likide.bbgraph.core.business.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.PermissionFactory;
import org.springframework.security.acls.model.Permission;

public abstract class AbstractGenericPermissionEvaluator<E extends GenericEntity<Long, ?>>
		implements IGenericPermissionEvaluator<User, E> {

	@Autowired
	protected PermissionFactory permissionFactory;

	protected final boolean is(Permission permission, String ... permissionNames) {
		return is(permission, Arrays.asList(permissionNames));
	}

	protected final boolean is(Permission permission, Collection<String> permissionNames) {
		for (String permissionName : permissionNames) {
			Permission permissionFromName = permissionFactory.buildFromName(permissionName);
			if (permissionFromName.equals(permission)) {
				return true;
			}
		}
		return false;
	}

}
