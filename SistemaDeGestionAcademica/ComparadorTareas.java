package SistemaDeGestionAcademica;

import java.util.Comparator;

public class ComparadorTareas implements Comparator<Tarea> {

    @Override
    public int compare(Tarea t1, Tarea t2) {

        int urgencia = Integer.compare(t2.getNivelUrgencia(), t1.getNivelUrgencia());
        if (urgencia != 0) return urgencia;

        return t1.getPrioridad().compareTo(t2.getPrioridad());
    }
}
