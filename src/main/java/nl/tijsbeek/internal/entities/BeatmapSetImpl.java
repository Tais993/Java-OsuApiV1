package nl.tijsbeek.internal.entities;

import nl.tijsbeek.api.entities.Beatmap;
import nl.tijsbeek.api.entities.BeatmapApproved;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public record BeatmapSetImpl(List<Beatmap> beatmaps,

                             BeatmapApproved approved,

                             String submitDateString,
                             String approvedDateString,
                             String lastUpdateString,

                             String artist,
                             long id,

                             String creatorName,
                             String creatorId,

                             String source,
                             int genreId,
                             int languageId,

                             String title,
                             String name,

                             List<String> tags,

                             int favouriteCount,
                             double rating

) implements BeatmapSet {

    public BeatmapSetImpl(@NotNull List<Beatmap> beatmaps) {
        this(beatmaps, null, null, null,
                null, null, 0, null,
                null, null, 0, 0,
                null, null, null, 0,
                0);
    }

    public BeatmapSetImpl(@NotNull List<Beatmap> beatmaps, @NotNull Beatmap beatmap) {
        this(beatmaps, beatmap.approved(), beatmap.submitDateString(),
                beatmap.approvedDateString(), beatmap.lastUpdateString(),
                beatmap.artist(), beatmap.beatmapSetId(), beatmap.creatorName(),
                beatmap.creatorId(), beatmap.source(), beatmap.genreId(),
                beatmap.languageId(), beatmap.title(), beatmap.title(),
                beatmap.tags(), beatmap.favouriteCount(), beatmap.rating());
    }

    @NotNull
    @Override
    @Contract(pure = true)
    public Iterator<Beatmap> iterator() {
        return beatmaps.iterator();
    }
}
