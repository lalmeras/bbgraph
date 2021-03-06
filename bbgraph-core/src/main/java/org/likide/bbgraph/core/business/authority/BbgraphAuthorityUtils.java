package org.likide.bbgraph.core.business.authority;

import java.util.ArrayList;
import java.util.List;

import org.iglooproject.jpa.security.business.authority.model.Authority;
import org.iglooproject.jpa.security.business.authority.service.IAuthorityService;
import org.iglooproject.jpa.security.business.authority.util.CoreAuthorityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableList;

@Component("bbgraphAuthorityUtils")
public class BbgraphAuthorityUtils {

	public static final List<String> PUBLIC_AUTHORITIES = ImmutableList.of(
		CoreAuthorityConstants.ROLE_AUTHENTICATED,
		CoreAuthorityConstants.ROLE_ADMIN
	);

	@Autowired
	private IAuthorityService authorityService;

	public List<Authority> getPublicAuthorities() {
		List<Authority> publicAuthorities = new ArrayList<Authority>();
		
		for (String authorityName : PUBLIC_AUTHORITIES) {
			Authority authority = authorityService.getByName(authorityName);
			if (authority != null) {
				publicAuthorities.add(authority);
			}
		}
		
		return publicAuthorities;
	}

}
