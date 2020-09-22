package vn.elca.training.model.exception;

import lombok.Getter;

@Getter
public class ConcurrentUpdateException extends Exception {

    public static final String MESSAGE = "This project was updated by another person";

    private Short projectNumber;

    public ConcurrentUpdateException(Short projectNumber) {
        this.projectNumber = projectNumber;
    }
}
