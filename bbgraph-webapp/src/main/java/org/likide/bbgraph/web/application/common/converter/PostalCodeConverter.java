package org.likide.bbgraph.web.application.common.converter;

import java.util.Locale;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.converter.AbstractConverter;
import org.likide.bbgraph.core.business.common.model.PostalCode;
import org.likide.bbgraph.web.application.common.validator.PostalCodeValidator;
import org.springframework.util.StringUtils;

public final class PostalCodeConverter extends AbstractConverter<PostalCode> {

	private static final long serialVersionUID = 7575610087030468757L;

	private static final PostalCodeConverter INSTANCE = new PostalCodeConverter();

	public static PostalCodeConverter get() {
		return INSTANCE;
	}

	private PostalCodeConverter() {
	}

	@Override
	public PostalCode convertToObject(String value, Locale locale) throws ConversionException {
		String trimmedValue = StringUtils.trimAllWhitespace(value);
		if (StringUtils.hasText(trimmedValue)) {
			if (!PostalCodeValidator.getInstance().isValid(trimmedValue)) {
				throw newConversionException("Invalid post code format", value, locale)
					.setResourceKey("common.validator.postalCode");
			}
			return new PostalCode(trimmedValue);
		}
		return null;
	}

	@Override
	public String convertToString(PostalCode value, Locale locale) {
		if (value == null) {
			return null;
		}
		return value.getValue();
	}

	@Override
	protected Class<PostalCode> getTargetType() {
		return PostalCode.class;
	}

}