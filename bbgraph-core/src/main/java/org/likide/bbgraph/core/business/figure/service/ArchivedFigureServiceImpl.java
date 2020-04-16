package org.likide.bbgraph.core.business.figure.service;

import org.iglooproject.jpa.business.generic.service.GenericEntityServiceImpl;
import org.likide.bbgraph.core.business.figure.dao.IArchivedFigureDao;
import org.likide.bbgraph.jpa.model.ArchivedFigure;
import org.springframework.stereotype.Service;

@Service("archivedFigureService")
public class ArchivedFigureServiceImpl extends GenericEntityServiceImpl<Long, ArchivedFigure> implements IArchivedFigureService {

	public ArchivedFigureServiceImpl(IArchivedFigureDao figureDao) {
		super(figureDao);
	}

}
