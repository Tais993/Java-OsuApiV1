module Java.OsuApiV1.main {
    requires org.slf4j;
    requires org.jetbrains.annotations;
    requires com.github.benmanes.caffeine;
    requires reactor.core;
    requires java.xml.crypto;
    requires spring.webflux;
    requires spring.core;
    requires spring.web;

    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.reactivestreams;

    exports nl.tijsbeek.api.cache.cachers;
    exports nl.tijsbeek.api.cache.handler;
    exports nl.tijsbeek.api.cache.policy;

    exports nl.tijsbeek.api.entities;
    exports nl.tijsbeek.api.entities.beatmap;
    exports nl.tijsbeek.api.osu;
    exports nl.tijsbeek.api.requests;

    opens nl.tijsbeek.api.entities to com.fasterxml.jackson.databind;
    opens nl.tijsbeek.internal.entities to com.fasterxml.jackson.databind;
    opens nl.tijsbeek.internal.jackson to com.fasterxml.jackson.databind;
    opens nl.tijsbeek.api.entities.beatmap to com.fasterxml.jackson.databind;
}