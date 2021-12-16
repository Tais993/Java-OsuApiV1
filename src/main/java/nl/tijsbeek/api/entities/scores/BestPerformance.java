package nl.tijsbeek.api.entities.scores;

import nl.tijsbeek.api.entities.holders.IdHolder;

public interface BestPerformance extends BaseScore, IdHolder {
    float pp();
    boolean hasReplayAvailable();
    long beatmapId();
}