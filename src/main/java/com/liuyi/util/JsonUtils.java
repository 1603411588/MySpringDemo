package com.liuyi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonUtils {

	protected static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	private static final com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();

	public static <T> T unmarshal(String message, Class<T> type) {
		T result = null;
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			result = mapper.readValue(message, type);
		} catch (Exception e) {
			logger.error(message, e);
			throw new RuntimeException("Cannot unmarshal JSON object: " + message, e);
		}

		return result;
	}

	public static String marshal(Object obj) {
		if (obj == null) {
			logger.error("Marshal Object must not be null.");
			return null;
		}
		String result = null;
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			result = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			logger.error(obj.toString(), e);
			throw new RuntimeException("Cannot marshal Object: " + obj, e);
		}

		return result;
	}

	public static String marshalFormat(Object obj) {
		if (obj == null) {
			logger.error("MarshalFormat Object must not be null.");
			return null;
		}
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
		String result = null;
		try {
			result = writer.writeValueAsString(obj);
		} catch (Exception e) {
			logger.error(obj.toString(), e);
			throw new RuntimeException("Cannot marshalFormat Object: " + obj, e);
		}
		return result;
	}
}
