package nl.tijsbeek.api.entities;

public interface IdHolder {

    long id();

    default String idString() {
        return id() + "";
    }
}
