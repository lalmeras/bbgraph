package org.likide.bbgraph.jpa.model;

import java.util.Date;
import java.util.Optional;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.iglooproject.jpa.business.generic.model.GenericEntity;
import org.iglooproject.jpa.hibernate.usertype.StringClobType;
import org.likide.bbgraph.core.business.user.model.BasicUser;

@MappedSuperclass
public abstract class AbstractFigure<T extends GenericEntity<Long, T>> extends GenericEntity<Long, T> {

	private static final long serialVersionUID = -3395664130208093773L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private BasicUser author;

	private String name;

	@Type(type = StringClobType.TYPENAME)
	private String jps;

	@Type(type = StringClobType.TYPENAME)
	private String template;

	private Date savedOn;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJps() {
		return jps;
	}

	public void setJps(String jps) {
		this.jps = jps;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Date getSavedOn() {
		return savedOn;
	}

	public void setSavedOn(Date savedOn) {
		this.savedOn = savedOn;
	}

	@Override
	public String getDisplayName() {
		return Optional.ofNullable(name).orElse("");
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getNameForToString() {
		return Optional.ofNullable(name).orElse("");
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
