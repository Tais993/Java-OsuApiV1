package nl.tijsbeek.api.entities;

@FunctionalInterface
public interface IdHolder {

    long id();

    default String idString() {
        return String.valueOf(id());
    }
}
