package nl.tijsbeek.api.entities.exceptions;

public class EmptyResponseException extends RuntimeException {

    public EmptyResponseException(String message) {
        super(message);
    }
}