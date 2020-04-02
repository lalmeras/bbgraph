package org.likide.bbgraph.web.application.profile.component;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.likide.bbgraph.core.business.user.model.User;
import org.likide.bbgraph.core.security.service.ISecurityManagementService;
import org.likide.bbgraph.core.util.binding.Bindings;
import org.likide.bbgraph.web.application.BbgraphSession;
import org.likide.bbgraph.web.application.administration.form.UserPasswordEditPopup;
import org.iglooproject.wicket.markup.html.basic.CoreLabel;
import org.iglooproject.wicket.markup.html.link.EmailLink;
import org.iglooproject.wicket.markup.html.panel.GenericPanel;
import org.iglooproject.wicket.more.condition.Condition;
import org.iglooproject.wicket.more.markup.html.basic.DateLabel;
import org.iglooproject.wicket.more.markup.html.basic.DefaultPlaceholderPanel;
import org.iglooproject.wicket.more.markup.html.image.BooleanIcon;
import org.iglooproject.wicket.more.markup.html.link.BlankLink;
import org.iglooproject.wicket.more.markup.html.template.js.bootstrap.modal.behavior.AjaxModalOpenBehavior;
import org.iglooproject.wicket.more.model.BindingModel;
import org.iglooproject.wicket.more.util.DatePattern;
import org.wicketstuff.wiquery.core.events.MouseEvent;

public class ProfileDescriptionPanel extends GenericPanel<User> {

	private static final long serialVersionUID = -1923855993008983060L;

	@SpringBean
	private ISecurityManagementService securityManagementService;

	public ProfileDescriptionPanel(String id, IModel<User> userModel) {
		super(id, userModel);
		
		UserPasswordEditPopup<User> passwordEditPopup = new UserPasswordEditPopup<>("passwordEditPopup", userModel);
		add(passwordEditPopup);
		
		IModel<String> emailModel = BindingModel.of(userModel, Bindings.user().email());
		
		add(
			new BlankLink("passwordEdit")
				.add(new AjaxModalOpenBehavior(passwordEditPopup, MouseEvent.CLICK))
				.add(
					Condition.isTrue(Model.of(securityManagementService.getOptions(BbgraphSession.get().getUser()).isPasswordUserUpdateEnabled()))
						.thenShow()
				),
			
			new CoreLabel("username", BindingModel.of(userModel, Bindings.user().username()))
				.showPlaceholder(),
			new BooleanIcon("active", BindingModel.of(userModel, Bindings.user().active())),
			new EmailLink("email", emailModel),
			new DefaultPlaceholderPanel("emailPlaceholder").condition(Condition.modelNotNull(emailModel)),
			new CoreLabel("locale", BindingModel.of(userModel, Bindings.user().locale()))
				.showPlaceholder(),
			new DateLabel("creationDate", BindingModel.of(userModel, Bindings.user().creationDate()), DatePattern.SHORT_DATETIME)
				.showPlaceholder(),
			new DateLabel("lastUpdateDate", BindingModel.of(userModel, Bindings.user().lastUpdateDate()), DatePattern.SHORT_DATETIME)
				.showPlaceholder(),
			new DateLabel("lastLoginDate", BindingModel.of(userModel, Bindings.user().lastLoginDate()), DatePattern.SHORT_DATETIME)
				.showPlaceholder()
		);
	}

}
