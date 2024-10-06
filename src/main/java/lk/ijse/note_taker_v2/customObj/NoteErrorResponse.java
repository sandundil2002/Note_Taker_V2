package lk.ijse.note_taker_v2.customObj;

import java.io.Serializable;

public class NoteErrorResponse implements NoteResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
