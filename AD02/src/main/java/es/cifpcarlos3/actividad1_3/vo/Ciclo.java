package es.cifpcarlos3.actividad1_3.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ciclo {
    private String codigo;
    private String nombre;
    private int duracion;
    private String codigoFamilia;

    @Override
    public String toString() {
        return String.format("'%s', '%s', %d, '%s'", codigo, nombre, duracion, codigoFamilia);
    }
}

