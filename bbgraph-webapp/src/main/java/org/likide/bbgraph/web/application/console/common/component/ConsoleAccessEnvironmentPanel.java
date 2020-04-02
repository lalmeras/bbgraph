package org.likide.bbgraph.web.application.console.common.component;

import org.likide.bbgraph.web.application.common.component.EnvironmentPanel;

public class ConsoleAccessEnvironmentPanel extends EnvironmentPanel {

	private static final long serialVersionUID = 5325046851722206296L;

	public ConsoleAccessEnvironmentPanel(String id) {
		super(id);
	}

	@Override
	protected void onConfigure() {
		super.onConfigure();
		setVisible(true);
	}

}