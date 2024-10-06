package lk.ijse.note_taker_v2.service;

import lk.ijse.note_taker_v2.customObj.NoteResponse;
import lk.ijse.note_taker_v2.dto.impl.NoteDTO;

import java.util.List;

public interface NoteService {
    String saveNote(NoteDTO noteDTO);
    void updateNote(String id, NoteDTO noteDTO);
    boolean deleteNote(String id);
    NoteResponse getNoteById(String id);
    List<NoteDTO> getAllNotes();
}