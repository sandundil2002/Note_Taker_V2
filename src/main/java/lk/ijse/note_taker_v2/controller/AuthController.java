package lk.ijse.note_taker_v2.controller;

import lk.ijse.note_taker_v2.dto.impl.UserDTO;
import lk.ijse.note_taker_v2.exception.DataPersistFailedException;
import lk.ijse.note_taker_v2.jwtmodel.JWTAuthResponse;
import lk.ijse.note_taker_v2.jwtmodel.SignIn;
import lk.ijse.note_taker_v2.service.AuthenticationService;
import lk.ijse.note_taker_v2.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JWTAuthResponse> signUp(
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("profilePic") MultipartFile profilePic,
            @RequestPart ("role") String role ) {

        try {
            byte[] profilePicBytes = profilePic.getBytes();
            String base64ProfilePic = AppUtil.toBase64ProfilePic(profilePicBytes);

            UserDTO userDTO = new UserDTO();
            userDTO.setFirstName(firstName);
            userDTO.setLastName(lastName);
            userDTO.setEmail(email);
            userDTO.setPassword(password);
            userDTO.setPassword(passwordEncoder.encode(password));
            userDTO.setProfilePic(base64ProfilePic);
            userDTO.setRole(role);

            return ResponseEntity.ok(authenticationService.signUp(userDTO));
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignIn sign) {
        return ResponseEntity.ok(authenticationService.signIn(sign));
    }

    @PostMapping("refresh")
    public ResponseEntity<JWTAuthResponse> refreshToken (@RequestParam ("refreshToken") String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}
