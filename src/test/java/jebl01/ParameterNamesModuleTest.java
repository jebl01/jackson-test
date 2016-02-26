package jebl01;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jesblo on 2016-02-26.
 */
public class ParameterNamesModuleTest {

    @Test
    public void works() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String data = "{\"key1\": \"value1\", \"key2\": \"value2\"}";

        Map<String, String> result = mapper.readValue(data, HashMap.class);

        result.forEach((key, value) -> System.out.println(key + " : " + value));
    }

    @Test
    public void failsWithModule() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));

        String data = "{\"key1\": \"value1\", \"key2\": \"value2\"}";

        Map<String, String> result = mapper.readValue(data, HashMap.class);
        result.forEach((key, value) -> System.out.println(key + " : " + value));
    }

    @Test //even though this works in this test, in the whild, this is not always a valid workaround...
    public void worksWithModule() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));

        String data = "{\"key1\": \"value1\", \"key2\": \"value2\"}";

        Map<String, String> result = mapper.readValue(data, new TypeReference<Map<String, String>>() {});

        result.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
