package test.web;

import org.apache.wicket.protocol.http.WebApplication;
import org.iglooproject.wicket.more.test.WicketMoreWicketTester;

public class BbgraphWicketTester extends WicketMoreWicketTester {

	public BbgraphWicketTester(WebApplication application) {
		super(application);
	}

	public String modalPath(String path) {
		return path + ":container:dialog";
	}

	public String modalFormPath(String path) {
		return modalPath(path) + ":body:form";
	}

	public String breadCrumbPath() {
		return "breadCrumb:breadCrumbElementListView";
	}

	public String breadCrumbElementPath(int element) {
		return "breadCrumb:breadCrumbElementListView:" + element + ":breadCrumbElement";
	}
}
