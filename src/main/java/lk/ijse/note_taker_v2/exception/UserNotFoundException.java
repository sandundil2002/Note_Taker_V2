package lk.ijse.note_taker_v2.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {}

    public UserNotFoundException(String message) {}

    public UserNotFoundException(String message, Throwable cause) {}
}
