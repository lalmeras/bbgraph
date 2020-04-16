package org.likide.bbgraph.init;

import java.io.IOException;

import org.iglooproject.jpa.exception.SecurityServiceException;
import org.iglooproject.jpa.exception.ServiceException;
import org.likide.bbgraph.init.config.spring.BbgraphInitConfig;
import org.likide.bbgraph.init.util.DatabaseImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class BbgraphImportDatabaseMain extends AbstractBbgraphMain {

	private static final Logger LOGGER = LoggerFactory.getLogger(BbgraphImportDatabaseMain.class);

	@Autowired
	private DatabaseImportService databaseImportService;

	private BbgraphImportDatabaseMain() {
	}

	public static void main(String[] args) throws ServiceException, SecurityServiceException, IOException {
		new BbgraphImportDatabaseMain().run();
	}

	protected void run() {
		try (AnnotationConfigApplicationContext context = startContext("development", BbgraphInitConfig.class)) {
			context.getAutowireCapableBeanFactory().autowireBean(this);
			databaseImportService.importDirectory();
			
			LOGGER.info("Initialization complete");
		} catch (Throwable e) { // NOSONAR We just want to log the Exception/Error, no error handling here.
			LOGGER.error("Error during initialization", e);
			throw new IllegalStateException(e);
		}
	}

}
