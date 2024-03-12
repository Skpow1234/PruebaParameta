package com.parameta.pruebaTecnica.Services.Implementation;

import com.parameta.pruebaTecnica.DTO.EmpleadoDTO;
import com.parameta.pruebaTecnica.Mappers.EmpleadoMapper;
import com.parameta.pruebaTecnica.Model.Empleado;
import com.parameta.pruebaTecnica.Repository.EmpleadoRepository;
import com.parameta.pruebaTecnica.Services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO) {
        validarEmpleadoDTO(empleadoDTO);
        Empleado empleado = EmpleadoMapper.dtoToEntity(empleadoDTO);
        empleado = empleadoRepository.save(empleado);
        return EmpleadoMapper.entityToDto(empleado);
    }
    private void validarEmpleadoDTO(EmpleadoDTO empleadoDTO) {
        if (Objects.isNull(empleadoDTO.getNombres()) || empleadoDTO.getNombres().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del empleado no puede estar vacío.");
        }
        if (Objects.isNull(empleadoDTO.getApellidos()) || empleadoDTO.getApellidos().trim().isEmpty()) {
            throw new IllegalArgumentException("Los apellidos del empleado no pueden estar vacíos.");
        }
        if (Objects.isNull(empleadoDTO.getTipoDocumento()) || empleadoDTO.getTipoDocumento().trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de documento no puede estar vacío.");
        }
        if (Objects.isNull(empleadoDTO.getNumeroDocumento()) || empleadoDTO.getNumeroDocumento().trim().isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede estar vacío.");
        }
        if (Objects.isNull(empleadoDTO.getFechaNacimiento())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede estar vacía.");
        }
        if (Objects.isNull(empleadoDTO.getFechaVinculacionCompania())) {
            throw new IllegalArgumentException("La fecha de vinculación no puede estar vacía.");
        }
        if (Objects.isNull(empleadoDTO.getCargo()) || empleadoDTO.getCargo().trim().isEmpty()) {
            throw new IllegalArgumentException("El cargo no puede estar vacío.");
        }
        if (Objects.isNull(empleadoDTO.getSalario())) {
            throw new IllegalArgumentException("El salario no puede estar vacío.");
        }

        LocalDate fechaNacimiento = empleadoDTO.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ahora = LocalDate.now();
        if (Period.between(fechaNacimiento, ahora).getYears() < 18) {
            throw new IllegalArgumentException("El empleado debe ser mayor de edad.");
        }
    }
    private EmpleadoDTO calcularInformacionAdicional(EmpleadoDTO empleadoDTO) {
        LocalDate fechaNacimiento = empleadoDTO.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaVinculacion = empleadoDTO.getFechaVinculacionCompania().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ahora = LocalDate.now();

        Period edad = Period.between(fechaNacimiento, ahora);
        Period tiempoVinculacion = Period.between(fechaVinculacion, ahora);

        Map<String, Integer> infoAdicional = new HashMap<>();
        infoAdicional.put("edadAños", edad.getYears());
        infoAdicional.put("edadMeses", edad.getMonths());
        infoAdicional.put("edadDias", edad.getDays());
        infoAdicional.put("vinculacionAños", tiempoVinculacion.getYears());
        infoAdicional.put("vinculacionMeses", tiempoVinculacion.getMonths());
        infoAdicional.put("vinculacionDias", tiempoVinculacion.getDays());

        empleadoDTO.setInformacionAdicional(infoAdicional);
        return empleadoDTO;
    }

    @Override
    public List<EmpleadoDTO> listarTodosLosEmpleados() {
        return empleadoRepository.findAll().stream()
                .map(EmpleadoMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmpleadoDTO obtenerEmpleadoPorId(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        return EmpleadoMapper.entityToDto(empleado);
    }

    @Override
    public EmpleadoDTO actualizarEmpleado(Long id, EmpleadoDTO empleadoDTO) {
        Empleado empleadoExistente = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        Empleado empleadoActualizado = EmpleadoMapper.dtoToEntity(empleadoDTO);
        empleadoActualizado.setId(empleadoExistente.getId());
        empleadoRepository.save(empleadoActualizado);

        return EmpleadoMapper.entityToDto(empleadoActualizado);
    }

    @Override
    public void eliminarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }
}
