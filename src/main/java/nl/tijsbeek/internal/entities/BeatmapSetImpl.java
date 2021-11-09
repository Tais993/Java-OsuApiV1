package nl.tijsbeek.internal.entities;

import nl.tijsbeek.api.entities.Beatmap;
import nl.tijsbeek.api.entities.BeatmapApproved;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
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
    private static final Logger logger = LoggerFactory.getLogger(BeatmapSetImpl.class);

    public BeatmapSetImpl(@NotNull List<? extends Beatmap> beatmaps) {
        this(Collections.unmodifiableList(beatmaps), null, null, null,
                null, null, 0, null,
                null, null, 0, 0,
                null, null, null, 0,
                0);
    }

    public BeatmapSetImpl(@NotNull Beatmap... beatmaps) {
        this(List.of(beatmaps), isNonEmptyVarArg(beatmaps));
    }

    @Contract(pure = true)
    private static Beatmap isNonEmptyVarArg(Beatmap @NotNull ... beatmaps) {
        if (beatmaps.length == 0) {
            throw new IllegalArgumentException("Beatmap array must not be empty");
        }

        return beatmaps[0];
    }

    public BeatmapSetImpl(@NotNull List<? extends Beatmap> beatmaps, @NotNull Beatmap beatmap) {
        this(Collections.unmodifiableList(beatmaps), beatmap.approved(), beatmap.submitDateString(),
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