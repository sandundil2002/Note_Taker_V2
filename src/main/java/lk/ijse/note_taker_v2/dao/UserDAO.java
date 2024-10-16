package lk.ijse.note_taker_v2.dao;

import lk.ijse.note_taker_v2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
}