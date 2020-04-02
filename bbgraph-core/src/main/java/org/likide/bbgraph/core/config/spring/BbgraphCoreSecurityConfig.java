package org.likide.bbgraph.core.config.spring;

import static org.likide.bbgraph.core.property.BbgraphCorePropertyIds.SECURITY_PASSWORD_USER_FORBIDDEN_PASSWORDS;

import org.likide.bbgraph.core.business.user.model.BasicUser;
import org.likide.bbgraph.core.business.user.model.TechnicalUser;
import org.likide.bbgraph.core.business.user.model.User;
import org.likide.bbgraph.core.security.model.BbgraphPermission;
import org.likide.bbgraph.core.security.model.SecurityOptions;
import org.likide.bbgraph.core.security.service.BbgraphPermissionEvaluator;
import org.likide.bbgraph.core.security.service.BbgraphUserDetailsServiceImpl;
import org.likide.bbgraph.core.security.service.IBbgraphUserDetailsService;
import org.likide.bbgraph.core.security.service.ISecurityManagementService;
import org.likide.bbgraph.core.security.service.SecurityManagementServiceImpl;
import org.iglooproject.jpa.security.config.spring.DefaultJpaSecurityConfig;
import org.iglooproject.jpa.security.password.rule.SecurityPasswordRulesBuilder;
import org.iglooproject.jpa.security.runas.CoreRunAsManagerImpl;
import org.iglooproject.jpa.security.service.AuthenticationUsernameComparison;
import org.iglooproject.jpa.security.service.ICorePermissionEvaluator;
import org.iglooproject.jpa.security.service.NamedPermissionFactory;
import org.iglooproject.spring.property.service.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.access.intercept.RunAsManager;
import org.springframework.security.acls.domain.PermissionFactory;

@Configuration
@Import(DefaultJpaSecurityConfig.class)
public class BbgraphCoreSecurityConfig {

	@Autowired
	protected DefaultJpaSecurityConfig defaultJpaSecurityConfig;

	@Autowired
	protected IPropertyService propertyService;

	@Bean
	@Scope(proxyMode = ScopedProxyMode.INTERFACES)
	public ICorePermissionEvaluator permissionEvaluator() {
		return new BbgraphPermissionEvaluator();
	}

	@Bean
	public AuthenticationUsernameComparison authenticationUsernameComparison() {
		return AuthenticationUsernameComparison.CASE_SENSITIVE;
	}

	@Bean
	public IBbgraphUserDetailsService userDetailsService() {
		BbgraphUserDetailsServiceImpl userDetailsService = new BbgraphUserDetailsServiceImpl();
		userDetailsService.setAuthenticationUsernameComparison(authenticationUsernameComparison());
		return userDetailsService;
	}

	@Bean
	public PermissionFactory permissionFactory() {
		return new NamedPermissionFactory(BbgraphPermission.ALL);
	}

	@Bean
	public RunAsManager runAsManager() {
		CoreRunAsManagerImpl runAsManager = new CoreRunAsManagerImpl();
		runAsManager.setKey(defaultJpaSecurityConfig.getRunAsKey());
		return runAsManager;
	}

	@Bean
	public RunAsImplAuthenticationProvider runAsAuthenticationProvider() {
		RunAsImplAuthenticationProvider runAsAuthenticationProvider = new RunAsImplAuthenticationProvider();
		runAsAuthenticationProvider.setKey(defaultJpaSecurityConfig.getRunAsKey());
		return runAsAuthenticationProvider;
	}

	@Bean
	public ISecurityManagementService securityManagementService() {
		SecurityManagementServiceImpl securityManagementService = new SecurityManagementServiceImpl();
		securityManagementService
			.setOptions(
				TechnicalUser.class,
				new SecurityOptions()
					.passwordAdminRecovery()
					.passwordAdminUpdate()
					.passwordUserRecovery()
					.passwordUserUpdate()
					.passwordRules(
						SecurityPasswordRulesBuilder.start()
							.minMaxLength(User.MIN_PASSWORD_LENGTH, User.MAX_PASSWORD_LENGTH)
							.forbiddenUsername()
							.forbiddenPasswords(propertyService.get(SECURITY_PASSWORD_USER_FORBIDDEN_PASSWORDS))
							.build()
					)
			)
			.setOptions(
				BasicUser.class,
				new SecurityOptions()
					.passwordExpiration()
					.passwordHistory()
					.passwordUserRecovery()
					.passwordUserUpdate()
					.passwordRules(
						SecurityPasswordRulesBuilder.start()
							.minMaxLength(User.MIN_PASSWORD_LENGTH, User.MAX_PASSWORD_LENGTH)
							.forbiddenUsername()
							.forbiddenPasswords(propertyService.get(SECURITY_PASSWORD_USER_FORBIDDEN_PASSWORDS))
							.build()
					)
			)
			.setDefaultOptions(
				SecurityOptions.DEFAULT
			);
		return securityManagementService;
	}

}
