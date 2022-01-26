package nl.tijsbeek.internal.entities.beatmap;

import nl.tijsbeek.api.entities.beatmap.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public record BeatmapSetImpl(List<Beatmap> beatmaps,

                             BeatmapStatus status,

                             String submitDateString,
                             String approvedDateString,
                             String lastUpdateString,

                             String artist,
                             long beatmapSetId,

                             String creatorName,
                             long creatorId,

                             String source,
                             BeatmapGenre genre,
                             BeatmapLanguage language,

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
    public static BeatmapSet of(@NotNull final Beatmap beatmap) {
        return of(Collections.singletonList(beatmap));
    }

    @NotNull
    @Contract("_ -> new")
    public static BeatmapSet of(final @NotNull List<? extends @NotNull Beatmap> beatmaps) {
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
    public static BeatmapSet merge(@NotNull final BeatmapSet beatmapSet1, @NotNull final BeatmapSet beatmapSet2) {
        List<Beatmap> beatmaps = new ArrayList<>(beatmapSet1.beatmaps());
        beatmaps.addAll(beatmapSet2.beatmaps());

        return of(beatmaps);
    }

    private BeatmapSetImpl() {
        this(List.of(), null, null,
                null, null,
                null, 0L, null,
                0L, null, null,
                null, null, null,
                null, 0, 0.0D);
    }

    private BeatmapSetImpl(@NotNull final List<? extends Beatmap> beatmaps, @NotNull final Beatmap beatmap) {
        this(Collections.unmodifiableList(beatmaps), beatmap.status(), beatmap.submitDateString(),
                beatmap.approvedDateString(), beatmap.lastUpdateString(),
                beatmap.artist(), beatmap.beatmapSetId(), beatmap.creatorName(),
                beatmap.creatorId(), beatmap.source(), beatmap.genre(),
                beatmap.language(), beatmap.title(), beatmap.title(),
                beatmap.tags(), beatmap.favouriteCount(), beatmap.rating());
    }

    @Override
    @NotNull
    @UnmodifiableView
    @Contract(pure = true)
    public List<Beatmap> beatmaps() {
        return Collections.unmodifiableList(beatmaps);
    }

    @NotNull
    @Override
    @Contract(pure = true)
    public Iterator<Beatmap> iterator() {
        return beatmaps.iterator();
    }
}