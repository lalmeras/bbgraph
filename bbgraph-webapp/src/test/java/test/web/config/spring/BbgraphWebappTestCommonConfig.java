package test.web.config.spring;

import org.apache.wicket.protocol.http.WebApplication;
import org.likide.bbgraph.web.application.BbgraphApplication;
import org.likide.bbgraph.web.application.config.spring.BbgraphWebappConfig;
import org.iglooproject.wicket.more.config.spring.WicketMoreApplicationPropertyRegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
	BbgraphWebappConfig.class,
	WicketMoreApplicationPropertyRegistryConfig.class
})
public class BbgraphWebappTestCommonConfig {

	@Bean
	public WebApplication application() {
		return new BbgraphApplication();
	}

}
