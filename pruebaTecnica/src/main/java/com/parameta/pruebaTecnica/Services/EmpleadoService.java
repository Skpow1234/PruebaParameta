package com.parameta.pruebaTecnica.Services;

import com.parameta.pruebaTecnica.DTO.EmpleadoDTO;

import java.util.List;

public interface EmpleadoService {
    EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO);
    List<EmpleadoDTO> listarTodosLosEmpleados();
    EmpleadoDTO obtenerEmpleadoPorId(Long id);
    EmpleadoDTO actualizarEmpleado(Long id, EmpleadoDTO empleadoDTO);
    void eliminarEmpleado(Long id);
}
