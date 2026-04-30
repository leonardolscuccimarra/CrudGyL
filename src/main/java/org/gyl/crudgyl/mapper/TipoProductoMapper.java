package org.gyl.crudgyl.mapper;

import org.gyl.crudgyl.dto.tipoProducto.TipoProductoRequestDTO;
import org.gyl.crudgyl.dto.tipoProducto.TipoProductoResponseDTO;
import org.gyl.crudgyl.entity.TipoProducto;

public class TipoProductoMapper {
    private TipoProductoMapper(){}

    public static TipoProducto toEntity(TipoProductoRequestDTO dto){
        TipoProducto tipoProducto = new TipoProducto();
        tipoProducto.setNombre(dto.nombre());
        tipoProducto.setDescripcion(dto.descripcion());
        return tipoProducto;
    }

    public static TipoProductoResponseDTO toResponseDTO(TipoProducto tipoProducto){
        return new TipoProductoResponseDTO(
                tipoProducto.getId_tipo_producto(),
                tipoProducto.getNombre(),
                tipoProducto.getDescripcion(),
                tipoProducto.getFechaBaja()
        );
    }

    public static void updateEntity(TipoProducto tipoProducto, TipoProductoRequestDTO dto){
        tipoProducto.setNombre(dto.nombre());
        tipoProducto.setDescripcion(dto.descripcion());
    }
}
