package org.likide.bbgraph.core.business.figure.service;

import org.iglooproject.jpa.business.generic.service.GenericEntityServiceImpl;
import org.likide.bbgraph.core.business.figure.dao.IFigureDao;
import org.likide.bbgraph.jpa.model.Figure;
import org.springframework.stereotype.Service;

@Service("figureService")
public class FigureServiceImpl extends GenericEntityServiceImpl<Long, Figure> implements IFigureService {

	public FigureServiceImpl(IFigureDao figureDao) {
		super(figureDao);
	}

}
