package org.gyl.crudgyl.controller;

import jakarta.validation.Valid;
import org.gyl.crudgyl.dto.ClienteRequestDTO;
import org.gyl.crudgyl.dto.ClienteResponseDTO;
import org.gyl.crudgyl.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO crear(@Valid @RequestBody ClienteRequestDTO dto){
        return clienteService.crear(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteResponseDTO> listar(){
        return clienteService.listar();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO actualizar(@Valid @PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto){
        return clienteService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@Valid @PathVariable Long id){
        clienteService.eliminar(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO buscarPorID(@Valid @PathVariable Long id){
        return clienteService.buscarPorID(id);
    }

    @GetMapping("/nombre/{valor}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteResponseDTO> buscarPorNombre(@Valid @PathVariable String valor){
        return clienteService.buscarPorNombre(valor);
    }

    @GetMapping("/apellido/{valor}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteResponseDTO> buscarPorApellido(@Valid @PathVariable String valor){
        return clienteService.buscarPorApellido(valor);
    }

    @GetMapping("/correo/{valor}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO buscarPorCorreo(@Valid @PathVariable String valor){
        return clienteService.buscarPorCorreo(valor);
    }

    @GetMapping("/telefono/{valor}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteResponseDTO> buscarPorTelefono(@Valid @PathVariable String valor){
        return clienteService.buscarPorTelefono(valor);
    }

    @GetMapping("/direccion/{valor}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteResponseDTO> buscarPorDireccion(@Valid @PathVariable String valor){
        return clienteService.buscarPorDireccion(valor);
    }
}
