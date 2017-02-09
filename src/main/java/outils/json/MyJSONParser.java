/*
 * 
 * 
 * 
 */
package outils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 * MyJSONParser.java
 *
 */
public class MyJSONParser {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static JsonNode getJSON(String path) throws IOException {
		return getJSON(new File(path));
	}

	public static JsonNode getJSON(File file) throws IOException {
		JsonNode node = MAPPER.readTree(file);
		return node;
	}

	public static <T> T fileToObject(File file, Class<T> c) throws IOException {
		return MAPPER.readValue(file, c);
	}

	public static String objectToJSONString(Object o) throws JsonProcessingException {
		return MAPPER.writeValueAsString(o);
	}

	private MyJSONParser() {
	}

}
