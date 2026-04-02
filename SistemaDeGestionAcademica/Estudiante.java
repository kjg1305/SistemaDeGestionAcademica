package SistemaDeGestionAcademica;
import java.util.*;

public class Estudiante {

    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String tipoEstudiante;
    private String email;
    private String contraseña;
    private List<Materia> materias;

    public Estudiante(int id, String nombre, String apellido, int edad,
                      String tipoEstudiante, String email, String contraseña) {

        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.tipoEstudiante = tipoEstudiante;
        this.email = email;
        this.contraseña = contraseña;
        this.materias = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public void agregarMateria(Materia m) {
        materias.add(m);
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void verMaterias() {
        for (int i = 0; i < materias.size(); i++) {
            System.out.println((i + 1) + ". " + materias.get(i).getNombre());
        }
    }
}