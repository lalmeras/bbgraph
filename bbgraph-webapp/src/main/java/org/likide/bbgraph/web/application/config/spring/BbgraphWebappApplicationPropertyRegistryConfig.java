package org.likide.bbgraph.web.application.config.spring;

import static org.likide.bbgraph.web.application.property.BbgraphWebappPropertyIds.APPLICATION_THEME;
import static org.likide.bbgraph.web.application.property.BbgraphWebappPropertyIds.MAINTENANCE_URL;
import static org.likide.bbgraph.web.application.property.BbgraphWebappPropertyIds.PORTFOLIO_ITEMS_PER_PAGE;
import static org.likide.bbgraph.web.application.property.BbgraphWebappPropertyIds.PORTFOLIO_ITEMS_PER_PAGE_DESCRIPTION;

import org.likide.bbgraph.web.application.common.template.theme.BbgraphApplicationTheme;
import org.iglooproject.spring.config.spring.AbstractApplicationPropertyRegistryConfig;
import org.iglooproject.spring.property.service.IPropertyRegistry;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BbgraphWebappApplicationPropertyRegistryConfig extends AbstractApplicationPropertyRegistryConfig {

	@Override
	public void register(IPropertyRegistry registry) {
		registry.registerEnum(APPLICATION_THEME, BbgraphApplicationTheme.class, BbgraphApplicationTheme.BASIC);
		
		registry.registerInteger(PORTFOLIO_ITEMS_PER_PAGE, 20);
		registry.registerInteger(PORTFOLIO_ITEMS_PER_PAGE_DESCRIPTION, 20);
		
		registry.registerString(MAINTENANCE_URL);
	}

}
