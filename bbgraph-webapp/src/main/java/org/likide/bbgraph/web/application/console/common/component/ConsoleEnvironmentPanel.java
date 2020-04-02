package org.likide.bbgraph.web.application.console.common.component;

import org.likide.bbgraph.web.application.common.component.EnvironmentPanel;

public class ConsoleEnvironmentPanel extends EnvironmentPanel {

	private static final long serialVersionUID = -1099199206441256170L;

	public ConsoleEnvironmentPanel(String id) {
		super(id);
	}

	@Override
	protected void onConfigure() {
		super.onConfigure();
		setVisible(true);
	}

}