package org.likide.bbgraph.web.application;

import java.util.Locale;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.Page;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.resource.loader.ClassStringResourceLoader;
import org.likide.bbgraph.core.business.common.model.PostalCode;
import org.likide.bbgraph.core.business.history.model.atomic.HistoryEventType;
import org.likide.bbgraph.core.business.user.model.BasicUser;
import org.likide.bbgraph.core.business.user.model.TechnicalUser;
import org.likide.bbgraph.core.business.user.model.User;
import org.likide.bbgraph.core.business.user.model.UserGroup;
import org.likide.bbgraph.web.application.administration.page.AdministrationAnnouncementListPage;
import org.likide.bbgraph.web.application.administration.page.AdministrationBasicUserDetailPage;
import org.likide.bbgraph.web.application.administration.page.AdministrationBasicUserListPage;
import org.likide.bbgraph.web.application.administration.page.AdministrationTechnicalUserDetailPage;
import org.likide.bbgraph.web.application.administration.page.AdministrationTechnicalUserListPage;
import org.likide.bbgraph.web.application.administration.page.AdministrationUserGroupDetailPage;
import org.likide.bbgraph.web.application.administration.page.AdministrationUserGroupListPage;
import org.likide.bbgraph.web.application.common.converter.PostalCodeConverter;
import org.likide.bbgraph.web.application.common.renderer.AuthorityRenderer;
import org.likide.bbgraph.web.application.common.renderer.UserGroupRenderer;
import org.likide.bbgraph.web.application.common.renderer.UserRenderer;
import org.likide.bbgraph.web.application.common.template.favicon.ApplicationFaviconPackage;
import org.likide.bbgraph.web.application.common.template.resources.BbgraphResourcesPackage;
import org.likide.bbgraph.web.application.common.template.resources.styles.application.applicationaccess.ApplicationAccessScssResourceReference;
import org.likide.bbgraph.web.application.common.template.resources.styles.console.console.ConsoleScssResourceReference;
import org.likide.bbgraph.web.application.common.template.resources.styles.console.consoleaccess.ConsoleAccessScssResourceReference;
import org.likide.bbgraph.web.application.common.template.resources.styles.notification.NotificationScssResourceReference;
import org.likide.bbgraph.web.application.console.common.component.ConsoleAccessHeaderAdditionalContentPanel;
import org.likide.bbgraph.web.application.console.common.component.ConsoleHeaderAdditionalContentPanel;
import org.likide.bbgraph.web.application.console.common.component.ConsoleHeaderEnvironmentPanel;
import org.likide.bbgraph.web.application.console.notification.demo.page.ConsoleNotificationDemoIndexPage;
import org.likide.bbgraph.web.application.history.renderer.HistoryValueRenderer;
import org.likide.bbgraph.web.application.navigation.page.HomePage;
import org.likide.bbgraph.web.application.navigation.page.MaintenancePage;
import org.likide.bbgraph.web.application.profile.page.ProfilePage;
import org.likide.bbgraph.web.application.referencedata.page.ReferenceDataPage;
import org.likide.bbgraph.web.application.resources.application.BbgraphApplicationResources;
import org.likide.bbgraph.web.application.resources.business.BbgraphBusinessResources;
import org.likide.bbgraph.web.application.resources.common.BbgraphCommonResources;
import org.likide.bbgraph.web.application.resources.console.BbgraphConsoleResources;
import org.likide.bbgraph.web.application.resources.enums.BbgraphEnumResources;
import org.likide.bbgraph.web.application.resources.navigation.BbgraphNavigationResources;
import org.likide.bbgraph.web.application.resources.notifications.BbgraphNotificationResources;
import org.likide.bbgraph.web.application.security.login.page.SignInPage;
import org.likide.bbgraph.web.application.security.password.page.SecurityPasswordCreationPage;
import org.likide.bbgraph.web.application.security.password.page.SecurityPasswordExpirationPage;
import org.likide.bbgraph.web.application.security.password.page.SecurityPasswordRecoveryPage;
import org.likide.bbgraph.web.application.security.password.page.SecurityPasswordResetPage;
import org.iglooproject.jpa.more.business.history.model.embeddable.HistoryValue;
import org.iglooproject.jpa.security.business.authority.model.Authority;
import org.iglooproject.spring.property.service.IPropertyService;
import org.iglooproject.wicket.bootstrap4.console.navigation.page.ConsoleAccessDeniedPage;
import org.iglooproject.wicket.bootstrap4.console.navigation.page.ConsoleLoginFailurePage;
import org.iglooproject.wicket.bootstrap4.console.navigation.page.ConsoleLoginSuccessPage;
import org.iglooproject.wicket.bootstrap4.console.navigation.page.ConsoleSignInPage;
import org.iglooproject.wicket.bootstrap4.console.template.ConsoleConfiguration;
import org.iglooproject.wicket.more.application.CoreWicketAuthenticatedApplication;
import org.iglooproject.wicket.more.console.common.model.ConsoleMenuSection;
import org.iglooproject.wicket.more.link.descriptor.parameter.CommonParameters;
import org.iglooproject.wicket.more.markup.html.factory.AbstractComponentFactory;
import org.iglooproject.wicket.more.markup.html.pages.monitoring.DatabaseMonitoringPage;
import org.iglooproject.wicket.more.rendering.BooleanRenderer;
import org.iglooproject.wicket.more.rendering.EnumRenderer;
import org.iglooproject.wicket.more.rendering.LocaleRenderer;
import org.iglooproject.wicket.more.security.page.LoginFailurePage;
import org.iglooproject.wicket.more.security.page.LoginSuccessPage;
import org.iglooproject.wicket.more.util.convert.HibernateProxyAwareConverterLocator;
import org.iglooproject.wicket.more.util.listener.FormInvalidDecoratorListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.ImmutableList;

public class BbgraphApplication extends CoreWicketAuthenticatedApplication {
	
	public static final String NAME = "BbgraphApplication";
	
	@Autowired
	private IPropertyService propertyService;
	
	public static BbgraphApplication get() {
		final Application application = Application.get();
		if (application instanceof BbgraphApplication) {
			return (BbgraphApplication) application;
		}
		throw new WicketRuntimeException("There is no BbgraphApplication attached to current thread " +
			Thread.currentThread().getName());
	}
	
	@Override
	public void init() {
		super.init();
		
		// si on n'est pas en développement, on précharge les feuilles de styles pour éviter la ruée et permettre le remplissage du cache
		if (!propertyService.isConfigurationTypeDevelopment()) {
			preloadStyleSheets(
				ConsoleScssResourceReference.get(),
				NotificationScssResourceReference.get(),
				ApplicationAccessScssResourceReference.get(),
				org.likide.bbgraph.web.application.common.template.resources.styles.application.basic.StylesScssResourceReference.get(),
				org.likide.bbgraph.web.application.common.template.resources.styles.application.advanced.StylesScssResourceReference.get()
			);
		}
		
		getResourceSettings().getStringResourceLoaders().addAll(
			0, // Override the keys in existing resource loaders with the following 
			ImmutableList.of(
				new ClassStringResourceLoader(BbgraphApplicationResources.class),
				new ClassStringResourceLoader(BbgraphBusinessResources.class),
				new ClassStringResourceLoader(BbgraphCommonResources.class),
				new ClassStringResourceLoader(BbgraphConsoleResources.class),
				new ClassStringResourceLoader(BbgraphEnumResources.class),
				new ClassStringResourceLoader(BbgraphNavigationResources.class),
				new ClassStringResourceLoader(BbgraphNotificationResources.class)
			)
		);
		
		FormInvalidDecoratorListener.init(this);
	}
	
	@Override
	protected IConverterLocator newConverterLocator() {
		ConverterLocator converterLocator = new ConverterLocator();
		
		converterLocator.set(Authority.class, AuthorityRenderer.get());
		converterLocator.set(User.class, UserRenderer.get());
		converterLocator.set(TechnicalUser.class, UserRenderer.get());
		converterLocator.set(BasicUser.class, UserRenderer.get());
		converterLocator.set(UserGroup.class, UserGroupRenderer.get());
		
		converterLocator.set(Locale.class, LocaleRenderer.get());
		converterLocator.set(Boolean.class, BooleanRenderer.get());
		
		converterLocator.set(HistoryValue.class, HistoryValueRenderer.get());
		converterLocator.set(HistoryEventType.class, EnumRenderer.get());
		
		converterLocator.set(PostalCode.class, PostalCodeConverter.get());
		
		return new HibernateProxyAwareConverterLocator(converterLocator);
	}

	@Override
	protected void mountApplicationPages() {
		
		// Sign in
		mountPage("/login/", getSignInPageClass());
		mountPage("/login/failure/", LoginFailurePage.class);
		mountPage("/login/success/", LoginSuccessPage.class);
		
		mountPage("/security/password/recovery/", SecurityPasswordRecoveryPage.class);
		mountPage("/security/password/expiration/", SecurityPasswordExpirationPage.class);
		mountParameterizedPage("/security/password/reset/", SecurityPasswordResetPage.class);
		mountParameterizedPage("/security/password/creation/", SecurityPasswordCreationPage.class);
		
		// Maintenance
		mountPage("/maintenance/", MaintenancePage.class);
		
		// Profile
		mountPage("/profile/", ProfilePage.class);
		
		// Reference data
		mountPage("/reference-data/", ReferenceDataPage.class);
		
		// Administration
		mountPage("/administration/basic-user/", AdministrationBasicUserListPage.class);
		mountParameterizedPage("/administration/basic-user/${" + CommonParameters.ID + "}/", AdministrationBasicUserDetailPage.class);
		mountPage("/administration/technical-user/", AdministrationTechnicalUserListPage.class);
		mountParameterizedPage("/administration/technical-user/${" + CommonParameters.ID + "}/", AdministrationTechnicalUserDetailPage.class);
		mountPage("/administration/user-group/", AdministrationUserGroupListPage.class);
		mountParameterizedPage("/administration/user-group/${" + CommonParameters.ID + "}/", AdministrationUserGroupDetailPage.class);
		mountPage("/administration/announcement/", AdministrationAnnouncementListPage.class);
		
		// Console sign in
		mountPage("/console/login/", ConsoleSignInPage.class);
		mountPage("/console/login/failure/", ConsoleLoginFailurePage.class);
		mountPage("/console/login/success/", ConsoleLoginSuccessPage.class);
		mountPage("/console/access-denied/", ConsoleAccessDeniedPage.class);
		
		// Console
		ConsoleConfiguration consoleConfiguration = ConsoleConfiguration.build("console", propertyService);
		consoleConfiguration.addCssResourceReference(ConsoleScssResourceReference.get());
		consoleConfiguration.addConsoleAccessCssResourceReference(ConsoleAccessScssResourceReference.get());
		consoleConfiguration.setConsoleAccessHeaderAdditionalContentComponentFactory(
			new AbstractComponentFactory<Component>() {
				private static final long serialVersionUID = 1L;
				@Override
				public Component create(String wicketId) {
					return new ConsoleAccessHeaderAdditionalContentPanel(wicketId);
				}
			}
		);
		consoleConfiguration.setConsoleHeaderEnvironmentComponentFactory(
			new AbstractComponentFactory<Component>() {
				private static final long serialVersionUID = 1L;
				@Override
				public Component create(String wicketId) {
					return new ConsoleHeaderEnvironmentPanel(wicketId);
				}
			}
		);
		consoleConfiguration.setConsoleHeaderAdditionalContentComponentFactory(
			new AbstractComponentFactory<Component>() {
				private static final long serialVersionUID = 1L;
				@Override
				public Component create(String wicketId) {
					return new ConsoleHeaderAdditionalContentPanel(wicketId);
				}
			}
		);
		
		ConsoleMenuSection notificationMenuSection = new ConsoleMenuSection(
			"notificationsMenuSection",
			"console.notifications",
			"notifications",
			ConsoleNotificationDemoIndexPage.class
		);
		consoleConfiguration.addMenuSection(notificationMenuSection);
		
		consoleConfiguration.mountPages(this);
		
		// Monitoring
		mountPage("/monitoring/db-access/", DatabaseMonitoringPage.class);
	}

	@Override
	protected void mountApplicationResources() {
		mountStaticResourceDirectory("/application", BbgraphResourcesPackage.class);
		
		// See favicon generator https://realfavicongenerator.net/
		mountResource("/android-chrome-192x192.png", new PackageResourceReference(ApplicationFaviconPackage.class, "android-chrome-192x192.png"));
		mountResource("/apple-touch-icon.png", new PackageResourceReference(ApplicationFaviconPackage.class, "apple-touch-icon.png"));
		mountResource("/browserconfig.xml", new PackageResourceReference(ApplicationFaviconPackage.class, "browserconfig.xml"));
		mountResource("/favicon-16x16.png", new PackageResourceReference(ApplicationFaviconPackage.class, "favicon-16x16.png"));
		mountResource("/favicon-32x32.png", new PackageResourceReference(ApplicationFaviconPackage.class, "favicon-32x32.png"));
		mountResource("/favicon.ico", new PackageResourceReference(ApplicationFaviconPackage.class, "favicon.ico"));
		mountResource("/manifest.json", new PackageResourceReference(ApplicationFaviconPackage.class, "manifest.json"));
		mountResource("/mstile-150x150.png", new PackageResourceReference(ApplicationFaviconPackage.class, "mstile-150x150.png"));
		mountResource("/safari-pinned-tab.svg", new PackageResourceReference(ApplicationFaviconPackage.class, "safari-pinned-tab.svg"));
	}

	@Override
	protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
		return BbgraphSession.class;
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	@Override
	public Class<? extends WebPage> getSignInPageClass() {
		return SignInPage.class;
	}

}
