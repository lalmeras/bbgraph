package org.likide.bbgraph.init.config.spring;

import org.likide.bbgraph.core.config.spring.BbgraphCoreCommonConfig;
import org.likide.bbgraph.init.BbgraphInitPackage;
import org.iglooproject.config.bootstrap.spring.annotations.IglooPropertySourcePriority;
import org.iglooproject.jpa.more.rendering.service.EmptyRendererServiceImpl;
import org.iglooproject.jpa.more.rendering.service.IRendererService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(
	name = IglooPropertySourcePriority.APPLICATION,
	value = "classpath:configuration-init.properties"
)
@Import({
	BbgraphCoreCommonConfig.class
})
@ComponentScan(
	basePackageClasses = {
		BbgraphInitPackage.class
	}
)
public class BbgraphInitConfig {

	@Bean
	public IRendererService rendererService() {
		return new EmptyRendererServiceImpl();
	}

}
