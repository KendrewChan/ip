package Errors;

import Errors.DukeException;

public class DeadlineException extends DukeException {
    public DeadlineException() {
        super("☹ OOPS!!! Please include a /by");
    }
}