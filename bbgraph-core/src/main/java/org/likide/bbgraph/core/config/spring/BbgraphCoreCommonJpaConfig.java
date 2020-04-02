package org.likide.bbgraph.core.config.spring;

import org.apache.lucene.search.SortField;
import org.likide.bbgraph.core.business.BbgraphCoreCommonBusinessPackage;
import org.likide.bbgraph.core.business.referencedata.model.ReferenceData;
import org.likide.bbgraph.core.business.referencedata.search.BasicReferenceDataSearchQueryImpl;
import org.likide.bbgraph.core.business.referencedata.search.IBasicReferenceDataSearchQuery;
import org.likide.bbgraph.core.config.hibernate.HibernateConfigPackage;
import org.iglooproject.jpa.config.spring.provider.JpaPackageScanProvider;
import org.iglooproject.jpa.more.business.sort.ISort;
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
			BbgraphCoreCommonBusinessPackage.class.getPackage(),
			HibernateConfigPackage.class.getPackage() // Typedef config
		);
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public <T extends ReferenceData<? super T>, S extends ISort<SortField>> IBasicReferenceDataSearchQuery<T, S> basicReferenceDataSearchQuery(Class<T> clazz) {
		return new BasicReferenceDataSearchQueryImpl<>(clazz);
	}

}
