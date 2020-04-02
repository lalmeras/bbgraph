package org.likide.bbgraph.core.business.announcement.predicate;

import java.util.Collection;

import org.likide.bbgraph.core.business.announcement.model.Announcement;
import org.likide.bbgraph.core.business.announcement.model.atomic.AnnouncementType;
import org.likide.bbgraph.core.util.binding.Bindings;
import org.iglooproject.functional.Predicates2;
import org.iglooproject.functional.SerializablePredicate2;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;


public final class AnnouncementPredicates {

	public static SerializablePredicate2<Announcement> active() {
		return Predicates2.notNullAnd(
			Predicates2.compose(Predicates2.isTrue(), Bindings.announcement().active())
		);
	}

	public static SerializablePredicate2<Announcement> inactive() {
		return Predicates2.notNullAndNot(active());
	}

	public static SerializablePredicate2<Announcement> type(AnnouncementType type, AnnouncementType ... otherTypes) {
		return type(ImmutableList.copyOf(Lists.asList(type, otherTypes)));
	}

	public static SerializablePredicate2<Announcement> type(Collection<AnnouncementType> types) {
		return Predicates2.notNullAnd(
			Predicates2.compose(Predicates2.in(types), Bindings.announcement().type())
		);
	}

	private AnnouncementPredicates() {
	}

}
