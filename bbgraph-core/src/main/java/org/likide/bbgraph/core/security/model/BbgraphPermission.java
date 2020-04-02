package org.likide.bbgraph.core.security.model;

import java.lang.reflect.Field;
import java.util.Collection;

import org.iglooproject.jpa.security.model.NamedPermission;

import com.google.common.collect.ImmutableSet;

public final class BbgraphPermission extends NamedPermission {

	private static final long serialVersionUID = 8541973919257428300L;

	public static final Collection<BbgraphPermission> ALL;
	static {
		ImmutableSet.Builder<BbgraphPermission> builder = ImmutableSet.builder();
		Field[] fields = BbgraphPermissionConstants.class.getFields();
		for (Field field : fields) {
			try {
				Object fieldValue = field.get(null);
				if (fieldValue instanceof String) {
					builder.add(new BbgraphPermission((String)fieldValue));
				}
			} catch (IllegalArgumentException|IllegalAccessException ignored) { // NOSONAR
			}
		}
		ALL = builder.build();
	}

	private BbgraphPermission(String name) {
		super(name);
	}

}
