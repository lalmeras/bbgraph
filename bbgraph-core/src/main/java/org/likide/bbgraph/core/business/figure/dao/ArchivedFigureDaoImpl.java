package org.likide.bbgraph.core.business.figure.dao;

import org.iglooproject.jpa.business.generic.dao.GenericEntityDaoImpl;
import org.likide.bbgraph.jpa.model.ArchivedFigure;
import org.springframework.stereotype.Component;

@Component("archivedFigureDao")
public class ArchivedFigureDaoImpl extends GenericEntityDaoImpl<Long, ArchivedFigure> implements IArchivedFigureDao {

}
