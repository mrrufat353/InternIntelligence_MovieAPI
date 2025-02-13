package az.movieapi.service.impl;

import az.movieapi.dto.request.AuthenticationRequest;
import az.movieapi.dto.request.RegisterRequest;
import az.movieapi.dto.response.AuthenticationResponse;
import az.movieapi.dto.response.UserResponse;
import az.movieapi.entity.User;
import az.movieapi.exception.CustomException;
import az.movieapi.jwt.JwtService;
import az.movieapi.repository.UserRepository;
import az.movieapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    @Override
    public UserResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new CustomException("User with email " + registerRequest.getEmail() + " already exists.");
        }

        if (registerRequest.getPassword() == null || registerRequest.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        System.out.println("Raw password before encoding: " + registerRequest.getPassword());

        User user = modelMapper.map(registerRequest, User.class);

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        System.out.println("Encoded password: " + encodedPassword);

        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponse.class);
    }


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException("User not found"));

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
