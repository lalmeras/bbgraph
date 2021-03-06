package org.likide.bbgraph.web.application.administration.component;

import static org.likide.bbgraph.web.application.property.BbgraphWebappPropertyIds.PORTFOLIO_ITEMS_PER_PAGE_DESCRIPTION;

import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.likide.bbgraph.core.business.user.model.User;
import org.likide.bbgraph.core.business.user.model.UserGroup;
import org.likide.bbgraph.core.business.user.service.IUserGroupService;
import org.likide.bbgraph.web.application.administration.form.UserAjaxDropDownSingleChoice;
import org.likide.bbgraph.web.application.administration.model.UserDataProvider;
import org.likide.bbgraph.web.application.administration.template.AdministrationUserDetailTemplate;
import org.likide.bbgraph.web.application.common.renderer.ActionRenderers;
import org.likide.bbgraph.web.application.common.util.CssClassConstants;
import org.iglooproject.spring.property.service.IPropertyService;
import org.iglooproject.wicket.markup.html.panel.GenericPanel;
import org.iglooproject.wicket.more.link.model.ComponentPageModel;
import org.iglooproject.wicket.more.markup.html.action.IOneParameterAjaxAction;
import org.iglooproject.wicket.more.markup.html.factory.AbstractParameterizedComponentFactory;
import org.iglooproject.wicket.more.markup.html.factory.IDetachableFactory;
import org.iglooproject.wicket.more.markup.html.feedback.FeedbackUtils;
import org.iglooproject.wicket.more.markup.html.form.LabelPlaceholderBehavior;
import org.iglooproject.wicket.more.markup.repeater.table.DecoratedCoreDataTablePanel.AddInPlacement;
import org.iglooproject.wicket.more.markup.repeater.table.builder.DataTableBuilder;
import org.iglooproject.wicket.more.model.GenericEntityModel;
import org.iglooproject.wicket.more.util.model.Detachables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserGroupDetailUsersPanel extends GenericPanel<UserGroup> {

	private static final long serialVersionUID = 1955579250974258074L;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserGroupDetailUsersPanel.class);

	@SpringBean
	private IUserGroupService userGroupService;

	@SpringBean
	private IPropertyService propertyService;

	private final UserDataProvider dataProvider;

	public UserGroupDetailUsersPanel(String id, final IModel<UserGroup> userGroupModel) {
		super(id, userGroupModel);
		setOutputMarkupId(true);
		
		dataProvider = new UserDataProvider();
		dataProvider.getGroupModel().setObject(userGroupModel.getObject());
		
		add(
			DataTableBuilder.start(dataProvider, dataProvider.getSortModel())
				.addLabelColumn(new ResourceModel("business.user.name"))
					.withLink(AdministrationUserDetailTemplate.mapper().setParameter2(new ComponentPageModel(this)))
					.withClass("text text-md")
				.addActionColumn()
					.addConfirmAction(ActionRenderers.remove())
						.title(new ResourceModel("administration.userGroup.detail.users.action.remove.confirmation.title"))
						.content(new IDetachableFactory<IModel<User>, IModel<String>>() {
							private static final long serialVersionUID = 1L;
							@Override
							public IModel<String> create(IModel<User> userModel) {
								return new StringResourceModel("administration.userGroup.detail.users.action.remove.confirmation.content")
									.setParameters(userModel.getObject().getFullName(), UserGroupDetailUsersPanel.this.getModelObject().getName());
							}
						})
						.confirm()
						.onClick(new IOneParameterAjaxAction<IModel<User>>() {
							private static final long serialVersionUID = 1L;
							@Override
							public void execute(AjaxRequestTarget target, IModel<User> parameter) {
								try {
									UserGroup userGroup = UserGroupDetailUsersPanel.this.getModelObject();
									User user = parameter.getObject();
									
									userGroupService.removeUser(userGroup, user);
									Session.get().success(getString("common.success"));
									throw new RestartResponseException(getPage());
								} catch (RestartResponseException e) {
									throw e;
								} catch (Exception e) {
									LOGGER.error("Unknown error occured while removing a group from the user", e);
									Session.get().error(getString("common.error.unexpected"));
									FeedbackUtils.refreshFeedback(target, getPage());
								}
							}
						})
						.hideLabel()
					.withClassOnElements(CssClassConstants.BTN_TABLE_ROW_ACTION)
					.end()
					.withClass("actions actions-1x")
				.bootstrapCard()
					.addIn(AddInPlacement.FOOTER_MAIN, new AbstractParameterizedComponentFactory<Component, Component>() {
						private static final long serialVersionUID = 1L;
						@Override
						public Component create(String wicketId, final Component table ) {
							return new UserGroupAddUserFragment(wicketId);
						}
					})
					.ajaxPager(AddInPlacement.HEADING_RIGHT)
					.count("administration.userGroup.detail.users.count")
				.build("results", propertyService.get(PORTFOLIO_ITEMS_PER_PAGE_DESCRIPTION))
		);
	}

	private class UserGroupAddUserFragment extends Fragment {
		
		private static final long serialVersionUID = 1L;
		
		public UserGroupAddUserFragment(String id) {
			super(id, "addUser", UserGroupDetailUsersPanel.this);
			
			IModel<User> userModel = new GenericEntityModel<>();
			
			add(
				new Form<User>("form", userModel)
					.add(
						new UserAjaxDropDownSingleChoice<>("user", userModel, User.class)
							.setRequired(true)
							.setLabel(new ResourceModel("business.user"))
							.add(new LabelPlaceholderBehavior()),
						
						new AjaxButton("add") {
							private static final long serialVersionUID = 1L;
							
							@Override
							protected void onSubmit(AjaxRequestTarget target) {
								UserGroup userGroup = UserGroupDetailUsersPanel.this.getModelObject();
								User user = userModel.getObject();
								
								if (user != null) {
									if (!user.getGroups().contains(userGroup)) {
										try {
											userGroupService.addUser(userGroup, user);
											Session.get().success(getString("common.success"));
										} catch (Exception e) {
											LOGGER.error("Error when adding a user to a user group.", e);
											Session.get().error(getString("common.error.unexpected"));
										}
									} else {
										LOGGER.error("User already added to this group.");
										Session.get().warn(getString("administration.userGroup.detail.users.action.add.error.duplicate"));
									}
								}
								
								userModel.setObject(null);
								userModel.detach();
								
								target.add(getPage());
								FeedbackUtils.refreshFeedback(target, getPage());
							}
							
							@Override
							protected void onError(AjaxRequestTarget target) {
								FeedbackUtils.refreshFeedback(target, getPage());
							}
						}
					)
			);
		}
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		Detachables.detach(dataProvider);
	}

}
