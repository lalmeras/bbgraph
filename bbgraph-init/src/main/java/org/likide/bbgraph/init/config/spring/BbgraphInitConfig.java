package org.likide.bbgraph.init.config.spring;

import org.iglooproject.config.bootstrap.spring.annotations.IglooPropertySourcePriority;
import org.iglooproject.jpa.more.rendering.service.EmptyRendererServiceImpl;
import org.iglooproject.jpa.more.rendering.service.IRendererService;
import org.likide.bbgraph.core.config.spring.BbgraphCoreCommonConfig;
import org.likide.bbgraph.init.BbgraphInitPackage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

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

	@Bean
	public NamedParameterJdbcTemplate jdbcTemplate() {
		HikariDataSource dataSource = new HikariDataSource();
		
		dataSource.setJdbcUrl("jdbc:sqlite:/home/lalmeras/bbgraph.sqlite?foreign_keys=true");
		dataSource.setMinimumIdle(0);
		dataSource.setMaximumPoolSize(1);
		dataSource.setConnectionTestQuery("SELECT 1");
		
		return new NamedParameterJdbcTemplate(dataSource);
	}

}
