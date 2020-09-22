package vn.elca.training.model.exception;

import lombok.Getter;

/**
 * @author dtx
 *
 */
@Getter
public class ProjectNumberAlreadyExistsException extends Exception {

    public static final String MESSAGE = "The project number already existed";

    private Short projectNumber;

    public ProjectNumberAlreadyExistsException(Short projectNumber) {
        this.projectNumber = projectNumber;
    }
}
