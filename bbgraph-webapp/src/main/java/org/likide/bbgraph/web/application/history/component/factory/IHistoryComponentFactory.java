package org.likide.bbgraph.web.application.history.component.factory;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

import org.likide.bbgraph.core.business.history.model.HistoryDifference;
import org.iglooproject.wicket.more.markup.html.factory.IOneParameterComponentFactory;

public interface IHistoryComponentFactory
	extends IOneParameterComponentFactory<Component, IModel<HistoryDifference>> {

}
