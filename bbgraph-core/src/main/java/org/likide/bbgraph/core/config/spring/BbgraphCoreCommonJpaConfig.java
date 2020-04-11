package org.likide.bbgraph.core.config.spring;

import org.apache.lucene.search.SortField;
import org.iglooproject.jpa.config.spring.provider.JpaPackageScanProvider;
import org.iglooproject.jpa.more.business.sort.ISort;
import org.likide.bbgraph.core.business.BbgraphCoreModelPackage;
import org.likide.bbgraph.core.business.referencedata.model.ReferenceData;
import org.likide.bbgraph.core.business.referencedata.search.BasicReferenceDataSearchQueryImpl;
import org.likide.bbgraph.core.business.referencedata.search.IBasicReferenceDataSearchQuery;
import org.likide.bbgraph.jpa.model.BbgraphJpaModelPackage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableAspectJAutoProxy
public class BbgraphCoreCommonJpaConfig {

	/**
	 * Déclaration des packages de scan pour l'application.
	 */
	@Bean
	public JpaPackageScanProvider applicationJpaPackageScanProvider() {
		return new JpaPackageScanProvider(
			BbgraphCoreModelPackage.class.getPackage(),
			BbgraphJpaModelPackage.class.getPackage()
		);
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public <T extends ReferenceData<? super T>, S extends ISort<SortField>> IBasicReferenceDataSearchQuery<T, S> basicReferenceDataSearchQuery(Class<T> clazz) {
		return new BasicReferenceDataSearchQueryImpl<>(clazz);
	}

}
