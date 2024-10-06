package lk.ijse.note_taker_v2.dao;

import lk.ijse.note_taker_v2.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDAO extends JpaRepository<NoteEntity, String> {}
