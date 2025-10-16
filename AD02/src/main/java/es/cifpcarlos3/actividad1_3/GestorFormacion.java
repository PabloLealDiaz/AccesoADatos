package es.cifpcarlos3.actividad1_3;

import es.cifpcarlos3.actividad1_3.vo.Ciclo;
import es.cifpcarlos3.actividad1_3.vo.FamiliaProfesional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorFormacion {

    public static void main(String[] args) {
        // Mostrar ruta de ejecución para depurar
        System.out.println("Ruta actual de ejecución: " + System.getProperty("user.dir"));

        if (args.length != 1) {
            System.out.println("Uso: java es.cifpcarlos3.actividad1_3.GestorFormacion <codigo_familia>");
            return;
        }

        String codigoFamilia = args[0].trim();
        FamiliaProfesional familia = cargarFamilia(codigoFamilia);

        if (familia == null) {
            System.out.println("No se encontró la familia profesional con código: " + codigoFamilia);
            return;
        }

        // Mostrar la familia encontrada
        System.out.println(familia);

        // Cargar los ciclos correspondientes a esa familia
        List<Ciclo> ciclos = cargarCiclosPorFamilia(codigoFamilia);

        System.out.println("Listado de ciclos:");
        if (ciclos.isEmpty()) {
            System.out.println("No hay ciclos asociados a esta familia.");
        } else {
            ciclos.forEach(System.out::println);
        }
    }

    private static FamiliaProfesional cargarFamilia(String codigo) {
        // Ruta adaptada a tu estructura
        String rutaFamilias = "src/main/java/es/cifpcarlos3/actividad1_3/familia_profesional.dat";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaFamilias))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;

                // Elimina BOM, comillas y espacios
                linea = linea.replace("\uFEFF", "").replace("'", "").trim();

                // Admite = ; o ,
                String[] partes = linea.split("[=;,]");
                if (partes.length < 2) continue;

                String cod = partes[0].trim();
                String nombre = partes[1].trim();

                // Depuración opcional
                // System.out.println("Leyendo familia -> [" + cod + "] = [" + nombre + "]");

                if (cod.equalsIgnoreCase(codigo)) {
                    return new FamiliaProfesional(cod, nombre);
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo familia_profesional.dat: " + e.getMessage());
        }
        return null;
    }

    private static List<Ciclo> cargarCiclosPorFamilia(String codigoFamilia) {
        List<Ciclo> lista = new ArrayList<>();

        // Ruta adaptada a tu estructura
        String rutaCiclos = "src/main/java/es/cifpcarlos3/actividad1_3/informacion_ciclos.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaCiclos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;

                // Elimina comillas simples y BOM
                linea = linea.replace("\uFEFF", "").replace("'", "").trim();

                // Admite coma o punto y coma como separador
                String[] partes = linea.split("[,;]");
                if (partes.length < 4) continue;

                String codigo = partes[0].trim();
                String nombre = partes[1].trim();
                int duracion = Integer.parseInt(partes[2].trim());
                String familia = partes[3].trim();

                if (familia.equalsIgnoreCase(codigoFamilia)) {
                    lista.add(new Ciclo(codigo, nombre, duracion, familia));
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo informacion_ciclos.txt: " + e.getMessage());
        }

        return lista;
    }
}


