package lk.ijse.note_taker_v2.service.impl;

import lk.ijse.note_taker_v2.dao.UserDAO;
import lk.ijse.note_taker_v2.dto.impl.UserDTO;
import lk.ijse.note_taker_v2.jwtmodel.JWTAuthResponse;
import lk.ijse.note_taker_v2.jwtmodel.SignIn;
import lk.ijse.note_taker_v2.service.AuthenticationService;
import lk.ijse.note_taker_v2.service.JWTService;
import lk.ijse.note_taker_v2.util.AppUtil;
import lk.ijse.note_taker_v2.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceIMPL implements AuthenticationService {
    private final UserDAO userDao;
    private final JWTService jwtService;
    private final MappingUtil mapping;
    private final AuthenticationManager authenticationManager;

    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(),signIn.getPassword()));
        var userByEmail = userDao.findByEmail(signIn.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var generatedToken = jwtService.generateToken(userByEmail);
        return JWTAuthResponse.builder().token(generatedToken).build() ;
    }

    @Override
    public JWTAuthResponse signUp(UserDTO signUpUser) {
        signUpUser.setUserId(AppUtil.generateUserID());
        var savedUser = userDao.save(mapping.userConvertToEntity(signUpUser));
        var genToken = jwtService.generateToken(savedUser);
        return JWTAuthResponse.builder().token(genToken).build();
    }

    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
        var userName = jwtService.extractUsername(accessToken);
        var userEntity =
                userDao.findByEmail(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var refreshToken = jwtService.refreshToken(userEntity);
        return JWTAuthResponse.builder().token(refreshToken).build();
    }
}
