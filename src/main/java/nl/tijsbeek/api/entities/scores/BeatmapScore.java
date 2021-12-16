package nl.tijsbeek.api.entities.scores;

import nl.tijsbeek.api.entities.Mod;
import nl.tijsbeek.api.entities.holders.IdHolder;

import java.util.Set;

public interface BeatmapScore extends BaseScore, IdHolder {
    String username();
    long userId();
    float pp();
    boolean hasReplayAvailable();
}