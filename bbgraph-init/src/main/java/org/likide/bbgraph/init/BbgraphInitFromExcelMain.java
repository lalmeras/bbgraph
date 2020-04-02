package org.likide.bbgraph.init;

import java.io.IOException;

import org.likide.bbgraph.init.config.spring.BbgraphInitConfig;
import org.likide.bbgraph.init.util.SpringContextWrapper;
import org.iglooproject.jpa.exception.SecurityServiceException;
import org.iglooproject.jpa.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.schlichtherle.truezip.file.TFile;

public final class BbgraphInitFromExcelMain extends AbstractBbgraphMain {

	private static final Logger LOGGER = LoggerFactory.getLogger(BbgraphInitFromExcelMain.class);

	private BbgraphInitFromExcelMain() {
	}

	public static void main(String[] args) throws ServiceException, SecurityServiceException, IOException {
		new BbgraphInitFromExcelMain().run();
	}

	protected void run() {
		try (AnnotationConfigApplicationContext context = startContext("development", BbgraphInitConfig.class)) {
			SpringContextWrapper contextWrapper = context.getBean("springContextWrapper", SpringContextWrapper.class);
			
			contextWrapper.openEntityManager();
			contextWrapper.importDirectory(new TFile( // May be inside a Jar
					BbgraphInitFromExcelMain.class.getResource("/init").toURI()
			));
			
			contextWrapper.reindexAll();
			
			LOGGER.info("Initialization complete");
		} catch (Throwable e) { // NOSONAR We just want to log the Exception/Error, no error handling here.
			LOGGER.error("Error during initialization", e);
			throw new IllegalStateException(e);
		}
	}

}
