package org.likide.bbgraph.jpa.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ArchivedFigure extends AbstractFigure<ArchivedFigure> {

	private static final long serialVersionUID = 7757767050388918252L;

	@ManyToOne
	private Figure figure;

	private Integer version;

	public Figure getFigure() {
		return figure;
	}

	public void setFigure(Figure figure) {
		this.figure = figure;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
