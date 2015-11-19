package com.dbalthassat.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class HibernateAwareObjectMapper extends ObjectMapper {

    public HibernateAwareObjectMapper() {
		Hibernate4Module module = new Hibernate4Module();
		setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.NONE);
		setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.ANY);
		module.disable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION);
		registerModule(module);
	}

}
