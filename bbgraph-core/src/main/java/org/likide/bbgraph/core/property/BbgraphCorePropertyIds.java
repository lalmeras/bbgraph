package org.likide.bbgraph.core.property;

import java.util.Date;
import java.util.List;

import org.likide.bbgraph.core.config.util.Environment;
import org.iglooproject.spring.property.model.AbstractPropertyIds;
import org.iglooproject.spring.property.model.ImmutablePropertyId;

public final class BbgraphCorePropertyIds extends AbstractPropertyIds {

	/*
	 * Mutable Properties
	 */

	// None

	/*
	 * Immutable Properties
	 */

	public static final ImmutablePropertyId<Date> BUILD_DATE = immutable("build.date");
	public static final ImmutablePropertyId<String> BUILD_SHA = immutable("build.sha");

	public static final ImmutablePropertyId<Environment> ENVIRONMENT = immutable("environment");

	public static final ImmutablePropertyId<Boolean> SECURITY_PASSWORD_VALIDATOR_ENABLED = immutable("security.password.validator.enabled");
	public static final ImmutablePropertyId<List<String>> SECURITY_PASSWORD_USER_FORBIDDEN_PASSWORDS = immutable("security.password.user.forbiddenPasswords");

}
