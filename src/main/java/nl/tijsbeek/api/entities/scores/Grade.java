package nl.tijsbeek.api.entities.scores;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public enum Grade {
    SS(),
    SSH(),
    S(),
    SH(),
    A(),
    B(),
    C(),
    D();

    @NonNls
    @NotNull
    @Override
    @Contract(pure = true)
    public String toString() {
        return "Grade{" +
                "name='" + name() + '\'' +
                '}';
    }
}