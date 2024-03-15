package com.parameta.pruebaTecnica.Model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Data
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;
    private Date fechaNacimiento;
    private Date fechaVinculacionCompania;
    private String cargo;
    private Double salario;
}
