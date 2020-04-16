package org.likide.bbgraph.init.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.iglooproject.jpa.exception.SecurityServiceException;
import org.iglooproject.jpa.exception.ServiceException;
import org.iglooproject.jpa.util.EntityManagerUtils;
import org.likide.bbgraph.core.business.figure.service.IFigureService;
import org.likide.bbgraph.init.model.GraphGraph;
import org.likide.bbgraph.jpa.model.Figure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class DatabaseImportService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseImportService.class);

	@Autowired
	private EntityManagerUtils entityManagerUtils;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private IFigureService figureService;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public void importDirectory() throws ServiceException, SecurityServiceException, ParseException {
		List<GraphGraph> graphs = jdbcTemplate.query("select * from graph_graph",
				(SqlParameterSource) null, new BeanPropertyRowMapper<GraphGraph>(GraphGraph.class));
		LOGGER.warn("{} graphs", graphs.size());
		entityManagerUtils.openEntityManager();
		SimpleDateFormat dateTimeSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			for (GraphGraph graph : graphs) {
				Figure figure = new Figure();
//				figure.setId(Long.valueOf(graph.getId()));
				figure.setJps(graph.getJps());
				figure.setName(graph.getName());
				if (graph.getSavedOn().length() == 10) {
					figure.setSavedOn(dateSdf.parse(graph.getSavedOn()));
				} else {
					figure.setSavedOn(dateTimeSdf.parse(graph.getSavedOn()));
				}
				// TODO: fix template field, or remove
				//figure.setTemplate(graph.getTemplate());
				figureService.create(figure);
			}
		} finally {
			entityManagerUtils.closeEntityManager();
		}
	}

	public void openEntityManager() {
		entityManagerUtils.openEntityManager();
	}

	public void closeEntityManager() {
		entityManagerUtils.closeEntityManager();
		entityManagerFactory.close();
	}

}
