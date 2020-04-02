package org.likide.bbgraph.web.application.common.form;

import java.util.Collection;

import org.apache.wicket.model.IModel;
import org.likide.bbgraph.core.business.referencedata.model.ReferenceData;
import org.likide.bbgraph.web.application.common.renderer.ReferenceDataRenderer;
import org.iglooproject.jpa.more.business.generic.model.search.EnabledFilter;
import org.iglooproject.wicket.more.markup.html.form.GenericEntityRendererToChoiceRenderer;
import org.iglooproject.wicket.more.markup.html.model.GenericReferenceDataModel;
import org.iglooproject.wicket.more.markup.html.select2.GenericSelect2DropDownSingleChoice;

public class ReferenceDataDropDownSingleChoice<T extends ReferenceData<? super T>> extends GenericSelect2DropDownSingleChoice<T> {

	private static final long serialVersionUID = -1857374154341270451L;

	public ReferenceDataDropDownSingleChoice(String id, IModel<T> model, Class<T> clazz) {
		this(
			id,
			model,
			new GenericReferenceDataModel<T>(clazz, EnabledFilter.ENABLED_ONLY)
		);
	}

	public ReferenceDataDropDownSingleChoice(String id, IModel<T> model, IModel<? extends Collection<? extends T>> choicesModel) {
		super(
			id,
			model,
			choicesModel,
			GenericEntityRendererToChoiceRenderer.of(ReferenceDataRenderer.get())
		);
		setNullValid(true);
	}

}
