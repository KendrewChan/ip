package Errors;

import Errors.DukeException;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("☹ OOPS!!! That is an invalid command");
    }
}