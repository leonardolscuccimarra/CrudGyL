package org.gyl.crudgyl.controller;

import org.gyl.crudgyl.dto.usuario.request.LoginRequestDTO;
import org.gyl.crudgyl.dto.usuario.request.RegistroRequestDTO;
import org.gyl.crudgyl.dto.usuario.response.RegistroResponseDTO;
import org.gyl.crudgyl.dto.usuario.response.TokenResponseDTO;
import org.gyl.crudgyl.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegistroResponseDTO registrar(@Valid @RequestBody RegistroRequestDTO dto) {
        return authenticationService.registrar(dto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponseDTO autenticar(@Valid @RequestBody LoginRequestDTO dto) {
        return authenticationService.login(dto);
    }
}