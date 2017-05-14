package codebytes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ResponseTransformer;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

/**
 * Helper class for converting objects to/from JSON.
 *
 * Implements Spark {@link ResponseTransformer} interface so it can be used
 * for JSON rendering of responses.
 */
public class JsonTransformer implements ResponseTransformer {
    private static final Logger LOG = LoggerFactory.getLogger(JsonTransformer.class);

    // jackson JSON serializer
    private ObjectMapper objectMapper;

    public JsonTransformer(ObjectMapper mapper) {
        this.objectMapper = mapper;
    }

    public JsonTransformer() {
        this(new ObjectMapper());
    }

    /**
     * Converts JSON string to specified argument type.
     *
     * Exceptions are wrapped in RuntimeException to lessen the need for
     * explicit checked-exception handling.
     */
    public <T> T fromJSON(String json, Class<T> cls) throws RuntimeException {
        try {
            return objectMapper.readValue(json, cls);
        } catch (IOException e) {
            throw new RuntimeException("Error processing JSON", e);
        }
    }

    public <T> List<T> listFromJSON(Reader source, Class<T> elemClass) throws IOException {
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        CollectionType collectionType =
                typeFactory.constructCollectionType(List.class, elemClass);

        return objectMapper.readValue(source, collectionType);
    }

    public <T> List<T> listFromJSON(String source, Class<T> elemClass) throws IOException {
        return listFromJSON(new StringReader(source), elemClass);
    }

    /** {@inheritDoc} */
    @Override
    public String render(Object model) throws RuntimeException {
        try {
            String json = objectMapper.writeValueAsString(model);
            LOG.debug("Serializing {} to '{}'", model, json);
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting to JSON", e);
        }
    }

}
