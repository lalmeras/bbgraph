package org.likide.bbgraph.web.application.common.template.theme.advanced;

import java.util.function.Supplier;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.likide.bbgraph.core.util.binding.Bindings;
import org.likide.bbgraph.web.application.BbgraphApplication;
import org.likide.bbgraph.web.application.BbgraphSession;
import org.likide.bbgraph.web.application.common.template.theme.common.AbstractNavbarPanel;
import org.likide.bbgraph.web.application.profile.page.ProfilePage;
import org.iglooproject.functional.SerializableFunction2;
import org.iglooproject.wicket.markup.html.basic.CoreLabel;
import org.iglooproject.wicket.more.link.descriptor.generator.IPageLinkGenerator;
import org.iglooproject.wicket.more.model.BindingModel;
import org.iglooproject.wicket.more.security.page.LogoutPage;

public class NavbarPanel extends AbstractNavbarPanel {

	private static final long serialVersionUID = 3273009208331893767L;

	public NavbarPanel(
		String id,
		Supplier<Class<? extends WebPage>> firstMenuPageSupplier
	) {
		super(id);
		
		add(
			BbgraphApplication.get().getHomePageLinkDescriptor()
				.link("home")
		);
		
		addNavItem(
			"profileLinkNavItem",
			ProfilePage.linkDescriptor(),
			firstMenuPageSupplier.get(),
			(SerializableFunction2<IPageLinkGenerator, Component>) pageLinkGenerator -> {
				return pageLinkGenerator
					.link("profileLink")
					.add(
						new CoreLabel("userFullName", BindingModel.of(BbgraphSession.get().getUserModel(), Bindings.user().fullName()))
							.hideIfEmpty()
					);
			}
		);
		
		add(
			new BookmarkablePageLink<Void>("logout", LogoutPage.class)
		);
	}

}
