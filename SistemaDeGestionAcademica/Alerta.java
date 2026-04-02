package SistemaDeGestionAcademica;

import java.util.List;

public class Alerta {

    public static void generarAlertas(List<Materia> materias) {

        boolean hay = false;

        for (Materia m : materias) {
            for (Tarea t : m.getTareas()) {

                if (t.isCompletada()) continue;

                long dias = t.diasRestantes();

                if (dias < 0) {
                    System.out.println(" VENCIDA en " + m.getNombre());
                    t.mostrarInfo();
                    hay = true;

                } else if (dias == 0) {
                    System.out.println(" ENTREGA HOY en " + m.getNombre());
                    t.mostrarInfo();
                    hay = true;

                } else if (dias <= 2) {
                    System.out.println(" PRÓXIMA en " + m.getNombre());
                    t.mostrarInfo();
                    hay = true;
                }
            }
        }

        if (!hay) System.out.println(" Sin alertas");
    }
}