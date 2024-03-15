package com.parameta.pruebaTecnica.Controller;

import com.parameta.pruebaTecnica.DTO.EmpleadoDTO;
import com.parameta.pruebaTecnica.Services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping
    public ResponseEntity<EmpleadoDTO> crearEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO empleadoCreado = empleadoService.crearEmpleado(empleadoDTO);
        return new ResponseEntity<>(empleadoCreado, HttpStatus.CREATED);
    }

    @GetMapping
    public List<EmpleadoDTO> listarTodosLosEmpleados() {
        return empleadoService.listarTodosLosEmpleados();
    }

    @GetMapping("/{id}")
    public EmpleadoDTO obtenerEmpleadoPorId(@PathVariable Long id) {
        return empleadoService.obtenerEmpleadoPorId(id);
    }

    @PutMapping("/{id}")
    public EmpleadoDTO actualizarEmpleado(@PathVariable Long id, @RequestBody EmpleadoDTO empleadoDTO) {
        return empleadoService.actualizarEmpleado(id, empleadoDTO);
    }
    @DeleteMapping("/{id}")
    public void eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
    }
}