package SistemaDeGestionAcademica;
import java.util.*;

public class Materia {

    private int id;
    private String nombre;
    private String descripcion;
    private String profesor;
    private String importancia;
    private List<Tarea> tareas;

    public Materia(int id, String nombre, String descripcion,
                   String profesor, String importancia) {

        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.profesor = profesor;
        this.importancia = importancia;
        this.tareas = new ArrayList<>();
    }
    

    public void mostrarInfo() {
        System.out.println("\nMateria: " + nombre);
        System.out.println("Profesor: " + profesor);
        System.out.println("Importancia: " + importancia);
        System.out.println("Descripción: " + descripcion);
    }

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public String getNombre() {
        return nombre;
    }

    public void ordenarTareasAutomaticamente() {
        Collections.sort(tareas, new ComparadorTareas());
    }

    public void mostrarTareas() {
        ordenarTareasAutomaticamente();
        for (Tarea t : tareas) {
            t.mostrarInfo();
        }
    }

    public void editarTarea(int index, String titulo, String desc, Prioridad prioridad) {
        Tarea t = tareas.get(index);
        t.setTitulo(titulo);
        t.setDescripcion(desc);
        t.setPrioridad(prioridad);
    }

    public void eliminarTarea(int index) {
        tareas.remove(index);
    }

    public void filtrarPorPrioridad(Prioridad p) {
        for (Tarea t : tareas) {
            if (t.getPrioridad() == p) {
                t.mostrarInfo();
            }
        }
    }

    public void mostrarEstadoMateria() {
        int hechas = 0;
        for (Tarea t : tareas) {
            if (t.isCompletada()) hechas++;
        }

        System.out.println("Materia: " + nombre);
        System.out.println("Progreso: " + hechas + "/" + tareas.size());
    }

    public double promedioTareas() {
        if (tareas.isEmpty()) return 0;

        int hechas = 0;
        for (Tarea t : tareas) {
            if (t.isCompletada()) hechas++;
        }

        return (double) hechas / tareas.size() * 100;
    }
}