package org.likide.bbgraph.web.application.common.renderer;

import java.util.Locale;

import org.iglooproject.wicket.more.rendering.Renderer;
import org.likide.bbgraph.core.business.user.model.UserGroup;

public abstract class UserGroupRenderer extends Renderer<UserGroup> {

	private static final long serialVersionUID = 5707691630314666729L;

	private static final Renderer<UserGroup> INSTANCE = new UserGroupRenderer() {
		private static final long serialVersionUID = 1L;
		@Override
		public String render(UserGroup value, Locale locale) {
			return value.getName();
		}
	}.nullsAsNull();

	public static Renderer<UserGroup> get() {
		return INSTANCE;
	}

	private UserGroupRenderer() {
	}

}
