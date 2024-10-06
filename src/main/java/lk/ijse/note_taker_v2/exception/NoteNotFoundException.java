package lk.ijse.note_taker_v2.exception;

public class NoteNotFoundException extends RuntimeException{
    public NoteNotFoundException() {}

    public NoteNotFoundException(String message) {}

    public NoteNotFoundException(String message, Throwable cause) {}
}
