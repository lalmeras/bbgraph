package org.likide.bbgraph.web.application.security.password.component;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.likide.bbgraph.core.business.user.model.User;
import org.likide.bbgraph.core.business.user.model.atomic.UserPasswordRecoveryRequestInitiator;
import org.likide.bbgraph.core.business.user.model.atomic.UserPasswordRecoveryRequestType;
import org.likide.bbgraph.core.business.user.service.IUserService;
import org.likide.bbgraph.core.security.service.ISecurityManagementService;
import org.likide.bbgraph.web.application.common.validator.EmailExistsValidator;
import org.iglooproject.wicket.more.application.CoreWicketAuthenticatedApplication;
import org.iglooproject.wicket.more.markup.html.feedback.FeedbackUtils;
import org.iglooproject.wicket.more.markup.html.form.LabelPlaceholderBehavior;
import org.iglooproject.wicket.more.util.model.Detachables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityPasswordRecoveryContentPanel extends Panel {

	private static final long serialVersionUID = 547223775134254240L;

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityPasswordRecoveryContentPanel.class);

	@SpringBean
	private IUserService userService;

	@SpringBean
	private ISecurityManagementService securityManagementService;

	private final IModel<String> emailModel = Model.of();

	public SecurityPasswordRecoveryContentPanel(String wicketId) {
		super(wicketId);
		
		Form<?> form = new Form<Void>("form");
		add(form);
		
		form.add(
			new TextField<>("email", emailModel)
				.setLabel(new ResourceModel("business.user.email"))
				.setRequired(true)
				.add(EmailAddressValidator.getInstance())
				.add(EmailExistsValidator.get())
				.add(new LabelPlaceholderBehavior())
		);
		
		form.add(
			new AjaxButton("validate", form) {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target) {
					try {
						User user = userService.getByEmailCaseInsensitive(emailModel.getObject());
						securityManagementService.initiatePasswordRecoveryRequest(
							user,
							UserPasswordRecoveryRequestType.RESET,
							UserPasswordRecoveryRequestInitiator.USER
						);
						
						Session.get().success(getString("security.password.recovery.validate.success"));
						
						throw CoreWicketAuthenticatedApplication.get().getSignInPageLinkDescriptor().newRestartResponseException();
					} catch (RestartResponseException e) {
						throw e;
					} catch (Exception e) {
						LOGGER.error("Error occurred while recovering password", e);
						Session.get().error(getString("common.error.unexpected"));
					}
					
					FeedbackUtils.refreshFeedback(target, getPage());
				}
				
				@Override
				protected void onError(AjaxRequestTarget target) {
					FeedbackUtils.refreshFeedback(target, getPage());
				}
			}
		);
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		Detachables.detach(
			emailModel
		);
	}

}
