package org.likide.bbgraph.core.config.spring;

import org.igloo.spring.autoconfigure.EnableIglooAutoConfiguration;
import org.likide.bbgraph.core.BbgraphCorePackage;
import org.iglooproject.config.bootstrap.spring.annotations.ManifestPropertySource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ManifestPropertySource(prefix = "bbgraph.core")
@Import({
	BbgraphCoreCommonJpaConfig.class,			// configuration de la persistence
	BbgraphCoreSecurityConfig.class,			// configuration de la sécurité
	BbgraphCoreTaskManagementConfig.class,			// configuration de la gestion des tâches
	BbgraphCoreNotificationConfig.class,		// configuration des notifications
	BbgraphCoreSchedulingConfig.class,			// configuration des tâches planifiées
	BbgraphCoreApplicationPropertyConfig.class	// configuration des propriétés de l'application
})
@ComponentScan(
	basePackageClasses = {
		BbgraphCorePackage.class
	},
	// https://jira.springsource.org/browse/SPR-8808
	// on veut charger de manière explicite le contexte ; de ce fait,
	// on ignore l'annotation @Configuration sur le scan de package.
	excludeFilters = @Filter(Configuration.class)
)
// fonctionnement de l'annotation @Transactional
@EnableTransactionManagement(order = BbgraphAdviceOrder.TRANSACTION)
@EnableIglooAutoConfiguration
public class BbgraphCoreCommonConfig {

	public static final String APPLICATION_NAME = "bbgraph";

	public static final String PROFILE_TEST = "test";

}