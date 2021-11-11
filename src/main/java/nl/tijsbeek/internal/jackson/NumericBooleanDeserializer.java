package nl.tijsbeek.internal.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class NumericBooleanDeserializer extends JsonDeserializer<Boolean> {
    private static final Logger logger = LoggerFactory.getLogger(NumericBooleanDeserializer.class);

    @Override
    public Boolean deserialize(@NotNull JsonParser p, DeserializationContext ctxt) throws IOException {
        return !"0".equals(p.getText());
    }
}