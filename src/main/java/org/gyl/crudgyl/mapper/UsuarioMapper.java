package org.gyl.crudgyl.mapper;

import org.gyl.crudgyl.dto.usuario.request.LoginRequestDTO;
import org.gyl.crudgyl.dto.usuario.request.RegistroRequestDTO;
import org.gyl.crudgyl.entity.Role;
import org.gyl.crudgyl.entity.Usuario;

public class UsuarioMapper {
    public UsuarioMapper() {}

    public static Usuario toEntity(RegistroRequestDTO dto, String password) {
        Usuario usuario = new Usuario();

        usuario.setUsername(dto.username());
        usuario.setPassword(password);
        usuario.setRol(Role.USER);

        return usuario;
    }

    public static void actualizarEntidad(Usuario usuario, LoginRequestDTO dto) {
        usuario.setUsername(dto.username());
        usuario.setPassword(dto.password());
    }
}