package az.movieapi.service;

import az.movieapi.dto.request.AuthenticationRequest;
import az.movieapi.dto.request.RegisterRequest;
import az.movieapi.dto.response.AuthenticationResponse;
import az.movieapi.dto.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    UserResponse register(RegisterRequest registerRequest);

    AuthenticationResponse authenticate(AuthenticationRequest auth);
}
