package gestion.bibliotheque.service;

public class AdherentRestrictionException extends RuntimeException {

    public AdherentRestrictionException() {
        super();
    }

    public AdherentRestrictionException(String message) {
        super(message);
    }
}