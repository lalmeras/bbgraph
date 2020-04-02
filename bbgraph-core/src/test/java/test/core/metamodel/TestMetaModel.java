package test.core.metamodel;

import org.likide.bbgraph.core.business.common.model.PostalCode;
import org.junit.Test;

import test.core.AbstractBbgraphTestCase;

public class TestMetaModel extends AbstractBbgraphTestCase {

	@Test
	public void testMetaModel() throws NoSuchFieldException, SecurityException {
		// Class<?> est utilisé sur GenericEntityReference ; ATTENTION,
		// l'annotation @Type est nécessaire pour un traitement correct par Hibernate.
		super.testMetaModel(PostalCode.class, Class.class, Comparable.class);
	}

}
