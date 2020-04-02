package org.likide.bbgraph.core.security.model;

import static org.iglooproject.commons.util.security.PermissionObject.DEFAULT_PERMISSION_OBJECT_NAME;

@SuppressWarnings("squid:S1192") // we won't use constants for string definition
public final class BbgraphSecurityExpressionConstants {
	
	public static final String READ = "hasPermission(#" + DEFAULT_PERMISSION_OBJECT_NAME + ", '" + BbgraphPermissionConstants.READ + "')";
	public static final String CREATE = "hasPermission(#" + DEFAULT_PERMISSION_OBJECT_NAME + ", '" + BbgraphPermissionConstants.CREATE + "')";
	public static final String WRITE = "hasPermission(#" + DEFAULT_PERMISSION_OBJECT_NAME + ", '" + BbgraphPermissionConstants.WRITE + "')";
	public static final String DELETE = "hasPermission(#" + DEFAULT_PERMISSION_OBJECT_NAME + ", '" + BbgraphPermissionConstants.DELETE + "')";

	private BbgraphSecurityExpressionConstants() {
	}

}
