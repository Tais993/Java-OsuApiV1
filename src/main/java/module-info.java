module Java.OsuApiVOne.main {
    requires org.slf4j;
    requires org.jetbrains.annotations;
    requires com.github.benmanes.caffeine;
    requires spring.webflux;
    requires spring.core;
    requires spring.web;

    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires reactor.core;

    exports nl.tijsbeek.api.cache.cachers;
    exports nl.tijsbeek.api.cache.handler;
    exports nl.tijsbeek.api.cache.policy;

    exports nl.tijsbeek.api.entities;
    exports nl.tijsbeek.api.entities.beatmap;
    exports nl.tijsbeek.api.entities.user;
    exports nl.tijsbeek.api.entities.scores;
    exports nl.tijsbeek.api.osu;
    exports nl.tijsbeek.api.requests;

    opens nl.tijsbeek.api.entities to com.fasterxml.jackson.databind;
    opens nl.tijsbeek.internal.entities.beatmap to com.fasterxml.jackson.databind;
    opens nl.tijsbeek.internal.entities.user to com.fasterxml.jackson.databind;
    opens nl.tijsbeek.internal.entities.scores to com.fasterxml.jackson.databind;
    opens nl.tijsbeek.internal.jackson to com.fasterxml.jackson.databind;
    opens nl.tijsbeek.api.entities.beatmap to com.fasterxml.jackson.databind;
    opens nl.tijsbeek.api.entities.user to com.fasterxml.jackson.databind;
    exports nl.tijsbeek.api.entities.holders;
    opens nl.tijsbeek.api.entities.holders to com.fasterxml.jackson.databind;
}