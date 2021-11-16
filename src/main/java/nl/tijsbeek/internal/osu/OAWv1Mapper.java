package nl.tijsbeek.internal.osu;

import nl.tijsbeek.api.entities.beatmap.Beatmap;
import nl.tijsbeek.api.entities.beatmap.BeatmapSet;
import nl.tijsbeek.internal.entities.beatmap.BeatmapImpl;
import nl.tijsbeek.internal.entities.beatmap.BeatmapSetImpl;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

enum OAWv1Mapper {
    ;

    @Contract("_ -> new")
    static Collection<BeatmapSet> mapToBeatmapSets(@NotNull final Collection<BeatmapImpl> beatmapImpls) {
        Objects.requireNonNull(beatmapImpls, "The given beatmapImpls cannot be null");

        return beatmapImpls.stream()
                .collect(
                        Collectors.groupingBy(BeatmapImpl::beatmapSetId,
                                Collectors.reducing(BeatmapSetImpl.empty(),
                                        BeatmapSetImpl::of,
                                        BeatmapSetImpl::merge)))
                .values()
                .stream().toList();
    }

    @NotNull
    @Contract("_ -> new")
    static Optional<BeatmapSet> mapToBeatmapSet(@NotNull final List<BeatmapImpl> beatmapImpls) {
        Objects.requireNonNull(beatmapImpls, "The given beatmapImpls cannot be null");

        Beatmap beatmap = beatmapImpls.get(0);

        return mapToBeatmapSets(beatmapImpls)
                .stream()
                .filter(beatmapSet -> beatmapSet.id() == beatmap.beatmapSetId())
                .findAny();
    }

    @NotNull
    @Contract("_ -> new")
    static Optional<Beatmap> mapToBeatmap(@NotNull final List<BeatmapImpl> beatmapImpls) {
        Objects.requireNonNull(beatmapImpls, "The given beatmapImpls cannot be null");

        return Optional.ofNullable(beatmapImpls.get(0));
    }
}