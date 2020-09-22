package vn.elca.training.model.exception;


import lombok.Getter;

import java.util.List;

/**
 * @author dtx
 *
 */
@Getter
public class EmployeeDoesNotExistsException extends Exception {

    public static final String MESSAGE = "The following visas do not exist";

    private List<String> employees;

    public EmployeeDoesNotExistsException(List<String> employees) {
        this.employees = employees;
    }
}
