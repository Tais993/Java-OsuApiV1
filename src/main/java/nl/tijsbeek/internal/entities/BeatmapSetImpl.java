package nl.tijsbeek.internal.entities;

import nl.tijsbeek.api.entities.Beatmap;
import nl.tijsbeek.api.entities.BeatmapApproved;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

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

    @NotNull
    @Contract(" -> new")
    public static BeatmapSet empty() {
        return new BeatmapSetImpl();
    }

    @NotNull
    @Contract("_ -> new")
    public static BeatmapSet of(@NotNull Beatmap beatmap) {
        return of(Collections.singletonList(beatmap));
    }

    @NotNull
    @Contract("_ -> new")
    public static BeatmapSet of(@NotNull List<? extends Beatmap> beatmaps) {
        if (beatmaps.isEmpty()) {
            throw new IndexOutOfBoundsException("Beatmap list is empty");
        }

        beatmaps.forEach(beatmap -> {
            Objects.requireNonNull(beatmap, "All elements in the Beatmap list must NOT be null");
        });

        Beatmap beatmap = beatmaps.get(0);

        return new BeatmapSetImpl(beatmaps, beatmap);
    }

    @NotNull
    @Contract("_, _ -> new")
    public static BeatmapSet merge(@NotNull BeatmapSet beatmapSet1, @NotNull BeatmapSet beatmapSet2) {
        List<Beatmap> beatmaps = new ArrayList<>(beatmapSet1.beatmaps());
        beatmaps.addAll(beatmapSet2.beatmaps());

        return of(beatmaps);
    }

    private BeatmapSetImpl() {
        this(null, null, null,
                null, null,
                null, 0L, null,
                null, null, 0,
                0, null, null,
                null, 0, 0.0D);
    }

    private BeatmapSetImpl(@NotNull List<? extends Beatmap> beatmaps, @NotNull Beatmap beatmap) {
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