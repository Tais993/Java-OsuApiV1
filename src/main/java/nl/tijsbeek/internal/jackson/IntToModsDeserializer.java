package nl.tijsbeek.internal.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import nl.tijsbeek.api.entities.Mod;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Set;

public class IntToModsDeserializer extends JsonDeserializer<Set<Mod>> {
    @Override
    public Set<Mod> deserialize(@NotNull JsonParser p, DeserializationContext ctxt) throws IOException {
        return Mod.fromBitwise(p.getValueAsLong(0L));
    }
}