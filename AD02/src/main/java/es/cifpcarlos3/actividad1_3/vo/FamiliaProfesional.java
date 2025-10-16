package es.cifpcarlos3.actividad1_3.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamiliaProfesional {
    private String codigo;
    private String nombre;

    @Override
    public String toString() {
        return "Familia profesional: " + nombre;
    }
}
