package lk.ijse.note_taker_v2.service;

import lk.ijse.note_taker_v2.dto.impl.UserDTO;
import lk.ijse.note_taker_v2.jwtmodel.JWTAuthResponse;
import lk.ijse.note_taker_v2.jwtmodel.SignIn;

public interface AuthenticationService {
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse signUp(UserDTO signUp);
    JWTAuthResponse refreshToken(String accessToken);
}