package Duke.Errors;

public class EmptyDescException extends DukeException {
    public EmptyDescException() {
        super("☹ OOPS!!! The description cannot be empty");
    }
}