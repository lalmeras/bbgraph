package org.likide.bbgraph.core.business.referencedata.search;

import org.apache.lucene.search.SortField;
import org.likide.bbgraph.core.business.referencedata.model.ReferenceData;
import org.iglooproject.jpa.more.business.referencedata.search.GenericReferenceDataSearchQueryImpl;
import org.iglooproject.jpa.more.business.sort.ISort;

import com.google.common.collect.ImmutableList;

public abstract class ReferenceDataSearchQueryImpl
		<
		T extends ReferenceData<? super T>,
		S extends ISort<SortField>,
		Q extends IReferenceDataSearchQuery<T, S, Q>
		>
		extends GenericReferenceDataSearchQueryImpl<T, S, Q> implements IReferenceDataSearchQuery<T, S, Q> {

	public ReferenceDataSearchQueryImpl(Class<? extends T> clazz) {
		super(clazz);
	}

	@Override
	public Q label(String label) {
		must(matchAutocompleteIfGiven(label, ImmutableList.of(ReferenceData.LABEL_FR_AUTOCOMPLETE, ReferenceData.LABEL_EN_AUTOCOMPLETE)));
		return thisAsQ();
	}

	@Override
	public Q code(String code) {
		must(matchIfGiven(ReferenceData.CODE, code));
		return thisAsQ();
	}

}
