package kz.eserzhanov.testtask.testtask.controller;

import kz.eserzhanov.testtask.testtask.config.security.jwt.JwtTokenProvider;
import kz.eserzhanov.testtask.testtask.database.entity.User;
import kz.eserzhanov.testtask.testtask.database.entity.dir.Role;
import kz.eserzhanov.testtask.testtask.exception.MapException;
import kz.eserzhanov.testtask.testtask.exception.SelfException;
import kz.eserzhanov.testtask.testtask.pojo.auth.AuthRequestPojo;
import kz.eserzhanov.testtask.testtask.pojo.auth.AuthResponsePojo;
import kz.eserzhanov.testtask.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthRestController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final MapException mapException;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthRestController(AuthenticationManager authenticationManager, UserService userService, MapException mapException, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.mapException = mapException;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestPojo request){
        try {
            String email = request.getLogin();
            User user = userService.getByLogin(email);
            List<Role> roles = new ArrayList<>();
            roles.add(user.getRole());
            String token = jwtTokenProvider.createToken(email, roles);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, request.getPassword()));

            AuthResponsePojo authResponseDto = new AuthResponsePojo(token, user.getRole().getRoleName());
            return ResponseEntity.status(HttpStatus.OK).body(authResponseDto);
        } catch (SelfException e){
            Map<String, String> map = e.getErrorMap();
            return mapException.getErrorResponse(map);
        }
    }
}
