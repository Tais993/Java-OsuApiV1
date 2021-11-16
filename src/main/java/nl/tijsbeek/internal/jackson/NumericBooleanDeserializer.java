package nl.tijsbeek.internal.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

public class NumericBooleanDeserializer extends JsonDeserializer<Boolean> {
    private static final Logger logger = LoggerFactory.getLogger(NumericBooleanDeserializer.class);

    @Override
    public Boolean deserialize(@NotNull final JsonParser p, final DeserializationContext ctxt) throws IOException {
        Objects.requireNonNull(p, "The given p cannot be null");

        return !("0".equals(p.getText()));
    }
}