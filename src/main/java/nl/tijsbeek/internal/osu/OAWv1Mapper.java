package nl.tijsbeek.internal.osu;

import nl.tijsbeek.api.entities.beatmap.Beatmap;
import nl.tijsbeek.internal.entities.BeatmapImpl;
import nl.tijsbeek.internal.entities.BeatmapSet;
import nl.tijsbeek.internal.entities.BeatmapSetImpl;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

enum OAWv1Mapper {
    ;

    @Contract("_ -> new")
    static Collection<BeatmapSet> mapToBeatmapSets(@NotNull Collection<BeatmapImpl> beatmapImpls) {
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
    static Optional<BeatmapSet> mapToBeatmapSet(@NotNull List<BeatmapImpl> beatmapImpls) {
        Beatmap beatmap = beatmapImpls.get(0);

        return mapToBeatmapSets(beatmapImpls)
                .stream()
                .filter(beatmapSet -> beatmapSet.id() == beatmap.beatmapSetId())
                .findAny();
    }

    @NotNull
    @Contract("_ -> new")
    static Optional<Beatmap> mapToBeatmap(@NotNull List<BeatmapImpl> beatmapImpls) {
        return Optional.ofNullable(beatmapImpls.get(0));
    }
}