package lk.ijse.note_taker_v2.controller;

import lk.ijse.note_taker_v2.customObj.UserResponse;
import lk.ijse.note_taker_v2.dto.impl.UserDTO;
import lk.ijse.note_taker_v2.exception.UserNotFoundException;
import lk.ijse.note_taker_v2.service.UserService;
import lk.ijse.note_taker_v2.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    //Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable ("id") String userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PatchMapping(value = "/{id}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUser(
            @PathVariable("id") String id,
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("profilePic") MultipartFile profilePic) {

        try {
            //Handle profile picture
            byte[] profilePicBytes = profilePic.getBytes();
            String base64ProfilePic = AppUtil.toBase64ProfilePic(profilePicBytes);

            //Build the user object
            UserDTO userDTO = new UserDTO();
            userDTO.setFirstName(firstName);
            userDTO.setLastName(lastName);
            userDTO.setEmail(email);
            userDTO.setPassword(password);
            userDTO.setProfilePic(base64ProfilePic);

            userService.updateUser(id, userDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}