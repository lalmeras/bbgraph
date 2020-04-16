package org.likide.bbgraph.core.business.figure.dao;

import org.iglooproject.jpa.business.generic.dao.GenericEntityDaoImpl;
import org.likide.bbgraph.jpa.model.Figure;
import org.springframework.stereotype.Component;

@Component("figureDao")
public class FigureDaoImpl extends GenericEntityDaoImpl<Long, Figure> implements IFigureDao {

}
