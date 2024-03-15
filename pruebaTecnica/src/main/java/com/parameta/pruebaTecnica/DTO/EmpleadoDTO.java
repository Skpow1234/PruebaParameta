package com.parameta.pruebaTecnica.DTO;
import lombok.*;
import java.util.Date;
import java.util.Map;
@Getter
@Data
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO {
    private Long id;
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;
    private Date fechaNacimiento;
    private Date fechaVinculacionCompania;
    private String cargo;
    private Double salario;
    private Map<String, Integer> informacionAdicional; // Para edad y tiempo de vinculación

}
