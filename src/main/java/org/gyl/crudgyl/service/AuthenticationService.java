package org.gyl.crudgyl.service;

import org.gyl.crudgyl.dto.usuario.request.LoginRequestDTO;
import org.gyl.crudgyl.dto.usuario.request.RegistroRequestDTO;
import org.gyl.crudgyl.dto.usuario.response.RegistroResponseDTO;
import org.gyl.crudgyl.dto.usuario.response.TokenResponseDTO;

public interface AuthenticationService {
    RegistroResponseDTO registrar(RegistroRequestDTO dto);

    TokenResponseDTO login(LoginRequestDTO dto);
}