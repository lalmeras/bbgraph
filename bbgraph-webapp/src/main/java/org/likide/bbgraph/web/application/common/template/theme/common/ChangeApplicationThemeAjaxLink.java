package org.likide.bbgraph.web.application.common.template.theme.common;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.likide.bbgraph.core.security.model.BbgraphAuthorityConstants;
import org.likide.bbgraph.web.application.common.template.theme.BbgraphApplicationTheme;
import org.likide.bbgraph.web.application.navigation.page.HomePage;
import org.likide.bbgraph.web.application.property.BbgraphWebappPropertyIds;
import org.iglooproject.spring.property.service.IPropertyService;
import org.iglooproject.wicket.more.condition.Condition;

public class ChangeApplicationThemeAjaxLink extends AjaxLink<Void> {

	private static final long serialVersionUID = 1L;

	@SpringBean
	private IPropertyService propertyService;

	public ChangeApplicationThemeAjaxLink(String id) {
		super(id);
		
		add(Condition.role(BbgraphAuthorityConstants.ROLE_ADMIN).thenShow());
	}

	@Override
	public void onClick(AjaxRequestTarget target) {
		try {
			BbgraphApplicationTheme applicationTheme = propertyService.get(BbgraphWebappPropertyIds.APPLICATION_THEME);
			
			if (applicationTheme == null) {
				return;
			}
			
			propertyService.set(BbgraphWebappPropertyIds.APPLICATION_THEME, applicationTheme.next());
			
			throw HomePage.linkDescriptor().newRestartResponseException();
		} catch (Exception e) {
			throw new IllegalStateException("Error on updating application theme.", e);
		}
	}

}
