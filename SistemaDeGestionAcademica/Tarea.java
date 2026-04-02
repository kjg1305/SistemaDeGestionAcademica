package SistemaDeGestionAcademica;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Tarea {

    private int id;
    private String titulo;
    private String descripcion;
    private String tipo;
    private String tema;
    private int tiempoEstimado;
    private LocalDate fechaEntrega;
    private boolean completada;
    private Prioridad prioridad;

    public Tarea(int id, String titulo, String descripcion, String tipo, String tema,
                 int tiempoEstimado, LocalDate fechaEntrega, Prioridad prioridad) {

        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.tema = tema;
        this.tiempoEstimado = tiempoEstimado;
        this.fechaEntrega = fechaEntrega;
        this.prioridad = prioridad;
        this.completada = false;
    }

    public void marcarComoHecha() {
        completada = true;
    }

    public boolean isCompletada() {
        return completada;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public long diasRestantes() {
        return ChronoUnit.DAYS.between(LocalDate.now(), fechaEntrega);
    }

    public int getNivelUrgencia() {
        long dias = diasRestantes();

        if (dias < 0) return 4;
        if (dias == 0) return 3;
        if (dias <= 2) return 2;
        return 1;
    }

    public String getEtiquetaUrgencia() {
        long dias = diasRestantes();

        if (dias < 0) return " VENCIDA";
        if (dias == 0) return "HOY";
        if (dias <= 2) return "PRÓXIMA";
        return "NORMAL";
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public void mostrarInfo() {
        System.out.println("[" + id + "] " + titulo);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Tipo: " + tipo + " | Tema: " + tema);
        System.out.println("Tiempo estimado: " + tiempoEstimado + "h");
        System.out.println("Entrega: " + fechaEntrega);
        System.out.println("Prioridad: " + prioridad);
        System.out.println("Urgencia: " + getEtiquetaUrgencia());
        System.out.println("Estado: " + (completada ? "Hecha" : "Pendiente"));
        System.out.println("----------------------");
    }
}