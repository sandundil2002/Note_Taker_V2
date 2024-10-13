package lk.ijse.note_taker_v2.service.impl;

import lk.ijse.note_taker_v2.customObj.UserErrorResponse;
import lk.ijse.note_taker_v2.customObj.UserResponse;
import lk.ijse.note_taker_v2.dao.UserDAO;
import lk.ijse.note_taker_v2.dto.impl.UserDTO;
import lk.ijse.note_taker_v2.entity.UserEntity;
import lk.ijse.note_taker_v2.exception.UserNotFoundException;
import lk.ijse.note_taker_v2.service.UserService;
import lk.ijse.note_taker_v2.util.AppUtil;
import lk.ijse.note_taker_v2.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {

    @Autowired
    private final UserDAO userDAO;

    @Autowired
    private final MappingUtil mappingUtil;

    @Override
    public String saveUser(UserDTO userDTO) {
        userDTO.setUserId(AppUtil.generateUserID());
        UserEntity saved = userDAO.save(mappingUtil.userConvertToEntity(userDTO));
        if(saved != null && saved.getUserId() != null ) {
            System.out.println("User saved successfully");
            return "User saved successfully";
        }else {
            System.out.println("Save failed");
            return "Save failed";
        }
    }

    @Override
    public void updateUser(String  id, UserDTO userDTO) {
        Optional<UserEntity> tmpUser = userDAO.findById(id);
        if (!tmpUser.isPresent()){
            System.out.println("User not found");
            throw new UserNotFoundException("User not found");
        } else {
            tmpUser.get().setFirstName(userDTO.getFirstName());
            tmpUser.get().setLastName(userDTO.getLastName());
            tmpUser.get().setEmail(userDTO.getEmail());
            tmpUser.get().setPassword(userDTO.getPassword());
            tmpUser.get().setProfilePic(userDTO.getProfilePic());
            userDAO.save(tmpUser.get());
            System.out.println("User updated successfully");
        }
    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> selectedUserId = userDAO.findById(userId);
        if(!selectedUserId.isPresent()){
            System.out.println("User not found");
            throw new UserNotFoundException("User not found");
        } else {
            System.out.println("User deleted successfully");
            userDAO.deleteById(userId);
        }
    }

    @Override
    public UserResponse getUserById(String id) {
        if(userDAO.existsById(id)){
            UserEntity userEntityByUserId = userDAO.getUserEntityByUserId(id);
            System.out.println(userEntityByUserId);
            return mappingUtil.userConvertToDTO(userEntityByUserId);
        }else {
            System.out.println("User not found");
            return new UserErrorResponse(0, "User not found");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userDAO.findAll();
        System.out.println(users);
        return mappingUtil.userConvertToDTOList(users);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return email -> userDAO.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

}
