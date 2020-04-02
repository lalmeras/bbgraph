package org.likide.bbgraph.web.application.common.component;

import org.apache.wicket.model.IModel;
import org.likide.bbgraph.core.business.announcement.model.Announcement;
import org.likide.bbgraph.web.application.common.renderer.AnnouncementRenderer;
import org.iglooproject.wicket.markup.html.basic.CoreLabel;
import org.iglooproject.wicket.markup.html.panel.GenericPanel;

public class AnnouncementMessagePanel extends GenericPanel<Announcement> {

	private static final long serialVersionUID = 7392973418033689115L;

	public AnnouncementMessagePanel(String id, IModel<Announcement> announcementModel) {
		super(id, announcementModel);
		setOutputMarkupId(true);
		
		add(
			new CoreLabel("title", AnnouncementRenderer.title().asModel(announcementModel)),
			new CoreLabel("description", AnnouncementRenderer.description().asModel(announcementModel))
		);
	}

}