package com.parameta.pruebaTecnica.Mappers;

import com.parameta.pruebaTecnica.DTO.EmpleadoDTO;
import com.parameta.pruebaTecnica.Model.Empleado;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EmpleadoMapper {

    public static Empleado dtoToEntity(EmpleadoDTO dto) {
        Empleado empleado = new Empleado();
        empleado.setId(dto.getId()); // Asegúrate de incluir el ID
        empleado.setNombres(dto.getNombres());
        empleado.setApellidos(dto.getApellidos());
        empleado.setTipoDocumento(dto.getTipoDocumento());
        empleado.setNumeroDocumento(dto.getNumeroDocumento());
        empleado.setFechaNacimiento(dto.getFechaNacimiento());
        empleado.setFechaVinculacionCompania(dto.getFechaVinculacionCompania());
        empleado.setCargo(dto.getCargo());
        empleado.setSalario(dto.getSalario());
        return empleado;
    }

    public static EmpleadoDTO entityToDto(Empleado empleado) {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setId(empleado.getId());
        dto.setNombres(empleado.getNombres());
        dto.setApellidos(empleado.getApellidos());
        dto.setTipoDocumento(empleado.getTipoDocumento());
        dto.setNumeroDocumento(empleado.getNumeroDocumento());
        dto.setFechaNacimiento(empleado.getFechaNacimiento());
        dto.setFechaVinculacionCompania(empleado.getFechaVinculacionCompania());
        dto.setCargo(empleado.getCargo());
        dto.setSalario(empleado.getSalario());

        // Calcular información adicional
        Map<String, Integer> infoAdicional = calcularInformacionAdicional(empleado.getFechaNacimiento(), empleado.getFechaVinculacionCompania());
        dto.setInformacionAdicional(infoAdicional);

        return dto;
    }

    private static Map<String, Integer> calcularInformacionAdicional(Date fechaNacimiento, Date fechaVinculacion) {
        LocalDate fechaNac = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaVinc = fechaVinculacion.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ahora = LocalDate.now();

        Period edad = Period.between(fechaNac, ahora);
        Period vinculacion = Period.between(fechaVinc, ahora);

        Map<String, Integer> info = new HashMap<>();
        info.put("edadAños", edad.getYears());
        info.put("edadMeses", edad.getMonths());
        info.put("edadDias", edad.getDays());
        info.put("vinculacionAños", vinculacion.getYears());
        info.put("vinculacionMeses", vinculacion.getMonths());
        info.put("vinculacionDias", vinculacion.getDays());

        return info;
    }
}
