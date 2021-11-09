package nl.tijsbeek.internal.entities;

import nl.tijsbeek.api.entities.IdHolder;
import nl.tijsbeek.api.entities.NameHolder;
import nl.tijsbeek.api.entities.beatmap.Beatmap;
import nl.tijsbeek.api.entities.beatmap.BeatmapStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public interface BeatmapSet extends IdHolder, NameHolder, Iterable<Beatmap> {
    @NotNull
    @Override
    @Contract(pure = true)
    Iterator<Beatmap> iterator();

    List<Beatmap> beatmaps();

    BeatmapStatus approved();

    String submitDateString();

    String approvedDateString();

    String lastUpdateString();

    String artist();

    long id();

    String creatorName();

    String creatorId();

    String source();

    int genreId();

    int languageId();

    String title();

    @NotNull String name();

    List<String> tags();

    int favouriteCount();

    double rating();
}