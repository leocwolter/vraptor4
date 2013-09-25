/***
 * Copyright (c) 2009 Caelum - www.caelum.com.br/opensource
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.caelum.vraptor.converter.jodatime;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.Locale;

import javax.inject.Inject;

import org.joda.time.DateMidnight;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.converter.ConversionException;
import br.com.caelum.vraptor.converter.ConversionMessage;

/**
 * VRaptor converter for {@link DateMidnight}. {@link DateMidnight} is part of Joda Time library.
 *
 * @author Lucas Cavalcanti
 * @author Otávio Scherer Garcia
 */
@Convert(DateMidnight.class)
public class DateMidnightConverter implements Converter<DateMidnight> {

	private Locale locale;

	@Deprecated // CDI eyes only
	public DateMidnightConverter() {
	}

	@Inject
	public DateMidnightConverter(Locale locale) {
		this.locale = locale;
	}

	@Override
	public DateMidnight convert(String value, Class<? extends DateMidnight> type) {
		if (isNullOrEmpty(value)) {
			return null;
		}
		
		try {
			return getFormatter().parseDateTime(value).toDateMidnight();
		} catch (Exception e) {
			throw new ConversionException(new ConversionMessage("is_not_a_valid_datetime", value));
		}
	}

	protected DateTimeFormatter getFormatter() {
		return DateTimeFormat.shortDate().withLocale(locale); 
	}
}