package lk.ijse.note_taker_v2.dto.impl;

import lk.ijse.note_taker_v2.customObj.NoteResponse;
import lk.ijse.note_taker_v2.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteDTO implements SuperDTO, NoteResponse {
    private String id;
    private String noteTitle;
    private String noteDescription;
    private String priorityLevel;
    private String createdDateTime;
    private String userId;
}
