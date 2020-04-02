package org.likide.bbgraph.web.application.administration.component;

import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.likide.bbgraph.core.business.user.model.TechnicalUser;
import org.likide.bbgraph.web.application.administration.form.UserAjaxDropDownSingleChoice;
import org.likide.bbgraph.web.application.administration.form.UserGroupDropDownSingleChoice;
import org.likide.bbgraph.web.application.administration.model.AbstractUserDataProvider;
import org.likide.bbgraph.web.application.administration.page.AdministrationTechnicalUserDetailPage;
import org.iglooproject.wicket.markup.html.form.PageableSearchForm;
import org.iglooproject.wicket.more.ajax.SerializableListener;
import org.iglooproject.wicket.more.common.behavior.UpdateOnChangeAjaxEventBehavior;
import org.iglooproject.wicket.more.link.descriptor.IPageLinkDescriptor;
import org.iglooproject.wicket.more.link.model.ComponentPageModel;
import org.iglooproject.wicket.more.markup.html.form.LabelPlaceholderBehavior;
import org.iglooproject.wicket.more.model.GenericEntityModel;
import org.iglooproject.wicket.more.util.model.Detachables;

public class TechnicalUserListSearchPanel extends Panel {

	private static final long serialVersionUID = -4624527265796845060L;

	private final IModel<TechnicalUser> quickAccessModel = new GenericEntityModel<>();

	public TechnicalUserListSearchPanel(String id, IPageable pageable, AbstractUserDataProvider<? extends TechnicalUser> dataProvider) {
		super(id);
		
		add(
			new PageableSearchForm<>("form", pageable)
				.add(
					new TextField<String>("name", dataProvider.getNameModel())
						.setLabel(new ResourceModel("business.user.name"))
						.add(new LabelPlaceholderBehavior()),
					new UserGroupDropDownSingleChoice("userGroup", dataProvider.getGroupModel())
						.setLabel(new ResourceModel("business.user.group"))
						.add(new LabelPlaceholderBehavior()),
					new CheckBox("active", dataProvider.getIncludeInactivesModel())
						.setLabel(new ResourceModel("administration.user.list.search.includeInactives"))
						.setOutputMarkupId(true),
					new UserAjaxDropDownSingleChoice<>("quickAccess", quickAccessModel, TechnicalUser.class)
						.setLabel(new ResourceModel("common.quickAccess"))
						.add(new LabelPlaceholderBehavior())
						.add(
							new UpdateOnChangeAjaxEventBehavior()
								.onChange(new SerializableListener() {
									private static final long serialVersionUID = 1L;
									@Override
									public void onBeforeRespond(Map<String, Component> map, AjaxRequestTarget target) {
										IPageLinkDescriptor linkDescriptor = AdministrationTechnicalUserDetailPage.MAPPER
											.setParameter2(new ComponentPageModel(TechnicalUserListSearchPanel.this))
											.map(new GenericEntityModel<>(quickAccessModel.getObject()));
										
										quickAccessModel.setObject(null);
										quickAccessModel.detach();
										
										if (linkDescriptor.isAccessible()) {
											throw linkDescriptor.newRestartResponseException();
										}
									}
								})
						)
				)
		);
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		Detachables.detach(quickAccessModel);
	}

}
