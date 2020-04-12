package org.likide.bbgraph.init.util;

import java.io.File;
import java.io.IOException;

import javax.persistence.EntityManagerFactory;

import org.iglooproject.jpa.exception.SecurityServiceException;
import org.iglooproject.jpa.exception.ServiceException;
import org.iglooproject.jpa.more.util.init.service.IImportDataService;
import org.iglooproject.jpa.search.service.IHibernateSearchService;
import org.iglooproject.jpa.util.EntityManagerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class SpringContextWrapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringContextWrapper.class);

	@Autowired
	private IHibernateSearchService hibernateSearchService;

	@Autowired
	private EntityManagerUtils entityManagerUtils;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private IImportDataService importDataService;

	public void importDirectory(File directory) throws ServiceException, SecurityServiceException, IOException {
		//importDataService.importDirectory(directory);
		
		Integer count = jdbcTemplate.queryForObject("select count(*) from graph_graph",
				(SqlParameterSource) null, (rs, i) -> rs.getInt(1));
		LOGGER.warn("{} graphs");
	}

	public void reindexAll() throws ServiceException {
		hibernateSearchService.reindexAll();
	}

	public void openEntityManager() {
		entityManagerUtils.openEntityManager();
	}

	public void closeEntityManager() {
		entityManagerUtils.closeEntityManager();
		entityManagerFactory.close();
	}

}
