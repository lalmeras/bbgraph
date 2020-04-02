package org.likide.bbgraph.web.application.administration.component;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.likide.bbgraph.core.business.user.model.BasicUser;
import org.likide.bbgraph.core.business.user.service.IUserService;
import org.likide.bbgraph.core.util.binding.Bindings;
import org.likide.bbgraph.web.application.administration.form.BasicUserPopup;
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

public class BasicUserDetailDescriptionPanel extends GenericPanel<BasicUser> {

	private static final long serialVersionUID = 8651898170121443991L;

	@SpringBean
	private IUserService userService;

	public BasicUserDetailDescriptionPanel(String id, final IModel<? extends BasicUser> userModel) {
		super(id, userModel);
		
		BasicUserPopup editPopup = new BasicUserPopup("editPopup");
		add(editPopup);
		
		IModel<String> emailModel = BindingModel.of(userModel, Bindings.user().email());
		
		add(
			new BlankLink("edit")
				.add(new AjaxModalOpenBehavior(editPopup, MouseEvent.CLICK) {
					private static final long serialVersionUID = 1L;
					@Override
					protected void onShow(AjaxRequestTarget target) {
						editPopup.setUpEdit(getModelObject());
					}
				})
		);
		
		add(
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
