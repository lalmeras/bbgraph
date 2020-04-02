package org.likide.bbgraph.web.application.common.renderer;

import java.util.Locale;

import org.likide.bbgraph.core.business.referencedata.model.ReferenceData;
import org.iglooproject.wicket.more.rendering.LocalizedTextRenderer;
import org.iglooproject.wicket.more.rendering.Renderer;

public abstract class ReferenceDataRenderer extends Renderer<ReferenceData<?>> {

	private static final long serialVersionUID = -3042035624376063917L;

	private static final Renderer<ReferenceData<?>> INSTANCE = new ReferenceDataRenderer() {
		private static final long serialVersionUID = 1L;
		@Override
		public String render(ReferenceData<?> value, Locale locale) {
			return LocalizedTextRenderer.get().render(value.getLabel(), locale);
		}
	}.nullsAsNull();

	private static final Renderer<ReferenceData<?>> CODE_LABEL = new ReferenceDataRenderer() {
		private static final long serialVersionUID = 1L;
		@Override
		public String render(ReferenceData<?> value, Locale locale) {
			String code = value.getCode();
			return (code != null ? code + " - " : "") + ReferenceDataRenderer.get().render(value, locale);
		}
	}.nullsAsNull();

	public static final Renderer<ReferenceData<?>> get() {
		return INSTANCE;
	}

	public static final Renderer<ReferenceData<?>> codeLabel() {
		return CODE_LABEL;
	}

	private ReferenceDataRenderer() {
	}

}
