package nl.tijsbeek.internal.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import nl.tijsbeek.api.entities.Mod;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Set;

public class IntToModsDeserializer extends JsonDeserializer<Set<Mod>> {
    private static final Logger logger = LoggerFactory.getLogger(IntToModsDeserializer.class);

    @Override
    public Set<Mod> deserialize(@NotNull final JsonParser p, final DeserializationContext ctxt) throws IOException {
        return Mod.fromBitwise(p.getValueAsLong(0L));
    }
}