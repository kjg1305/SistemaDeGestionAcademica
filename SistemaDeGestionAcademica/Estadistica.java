package SistemaDeGestionAcademica;

import java.util.*;

public class Estadistica {

    public static void mostrarProgreso(List<Materia> materias) {
        for (Materia m : materias) {
            System.out.println(m.getNombre() + ": " + m.promedioTareas() + "%");
        }
    }

    public static void rankingProductividad(List<Materia> materias) {

        List<Materia> copia = new ArrayList<>(materias);

        copia.sort((m1, m2) ->
                Double.compare(m2.promedioTareas(), m1.promedioTareas())
        );

        int pos = 1;

        for (Materia m : copia) {
            System.out.println(pos + ". " + m.getNombre() +
                    " → " + m.promedioTareas() + "%");
            pos++;
        }
    }
}