package lk.ijse.note_taker_v2.controller;

import lk.ijse.note_taker_v2.dto.impl.UserDTO;
import lk.ijse.note_taker_v2.jwtmodel.JWTResponse;
import lk.ijse.note_taker_v2.jwtmodel.SignIn;
import lk.ijse.note_taker_v2.util.AppUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JWTResponse> signUp(
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("profilePic") MultipartFile profilePic) {

        try {
            byte[] profilePicBytes = profilePic.getBytes();
            String base64ProfilePic = AppUtil.toBase64ProfilePic(profilePicBytes);

            UserDTO userDTO = new UserDTO();
            userDTO.setFirstName(firstName);
            userDTO.setLastName(lastName);
            userDTO.setEmail(email);
            userDTO.setPassword(password);
            userDTO.setProfilePic(base64ProfilePic);

            String status = userService.saveUser(userDTO);
            if (status.contains("User saved successfully")) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<JWTResponse> signIn(@RequestBody SignIn sign) {
        return null;
    }
}
