package org.likide.bbgraph.web.application.notification.component;

import java.util.Date;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.likide.bbgraph.core.business.user.model.User;
import org.likide.bbgraph.core.util.ResourceKeyGenerator;
import org.likide.bbgraph.core.util.binding.Bindings;
import org.iglooproject.wicket.markup.html.basic.CoreLabel;
import org.iglooproject.wicket.more.link.descriptor.generator.ILinkGenerator;
import org.iglooproject.wicket.more.markup.html.link.InvisibleLink;
import org.iglooproject.wicket.more.model.BindingModel;

public class UserPasswordRecoveryRequestHtmlNotificationPanel<T extends User> extends AbstractHtmlNotificationPanel<T> {

	private static final long serialVersionUID = -6941290354402094613L;

	public UserPasswordRecoveryRequestHtmlNotificationPanel(String id,
			ResourceKeyGenerator resourceKeyGenerator,
			final IModel<T> objectModel, final IModel<User> authorModel, final IModel<Date> dateModel,
			ILinkGenerator linkGenerator) {
		this(id, resourceKeyGenerator, resourceKeyGenerator, objectModel, authorModel, dateModel, linkGenerator);
	}

	public UserPasswordRecoveryRequestHtmlNotificationPanel(String id,
			ResourceKeyGenerator resourceKeyGenerator, ResourceKeyGenerator defaultResourceKeyGenerator,
			final IModel<T> objectModel, final IModel<User> authorModel, final IModel<Date> dateModel,
			ILinkGenerator linkGenerator) {
		super(id, objectModel);
		
		// Intro
		StringResourceModel descriptionTextModel = 
			new StringResourceModel(
					resourceKeyGenerator.resourceKey("text"),
					objectModel
			)
				.setParameters(dateModel, authorModel)
				.setDefaultValue(
					new StringResourceModel(
						defaultResourceKeyGenerator.resourceKey("text"),
						objectModel
					)
						.setParameters(dateModel, authorModel)
				);
		add(new CoreLabel("description", descriptionTextModel).setEscapeModelStrings(false));
		
		// Main link
		if (linkGenerator != null) {
			StringResourceModel linkIntroModel = new StringResourceModel(
				resourceKeyGenerator.resourceKey("link.intro"),
				objectModel
			)
				.setParameters(dateModel, authorModel)
				.setDefaultValue(
					new StringResourceModel(
						defaultResourceKeyGenerator.resourceKey("link.intro"),
						objectModel
					)
						.setParameters(dateModel, authorModel)
				);
			StringResourceModel linkLabelModel = new StringResourceModel(
				resourceKeyGenerator.resourceKey("link.label"),
				objectModel
			)
				.setParameters(dateModel, authorModel)
				.setDefaultValue(
					new StringResourceModel(
						defaultResourceKeyGenerator.resourceKey("link.label"),
						objectModel
					)
						.setParameters(dateModel, authorModel)
				);
			add(
				new CoreLabel("linkIntro", linkIntroModel),
				linkGenerator.link("mainLink")
					.setAbsolute(true)
					.setBody(linkLabelModel)
			);
		} else {
			add(new InvisibleLink<>("mainLink"));
		}
		
		add(
			new CoreLabel(
				"helpUsername",
				new StringResourceModel("notification.panel.user.password.recovery.request.help.username", BindingModel.of(objectModel, Bindings.user().username()))
			)
				.setEscapeModelStrings(false)
		);
	}

}
