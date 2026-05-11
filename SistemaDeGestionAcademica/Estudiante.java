package SistemaDeGestionAcademica;
import java.util.*;
import java.io.*;

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
    
    public String toLineaUsuario() {
        return id + "|" + nombre + "|" + apellido + "|" + edad + "|"
                + tipoEstudiante + "|" + email + "|" + contraseña;
    }

    public static Estudiante fromLineaUsuario(String linea) {
        String[] p = linea.split("\\|", -1);
        return new Estudiante(Integer.parseInt(p[0]), p[1], p[2],
                Integer.parseInt(p[3]), p[4], p[5], p[6]);
    }
    
    public void guardarDatos() {
        // Me puse a pelear con el codigo, aparentemente si colocas @ o . 
        //  se daña, asi que lo quito
        String carpeta = "data/homework/" + email.replace("@", "_").replace(".", "_");
        new File(carpeta).mkdirs();

        for (Materia m : materias) {
            // Nombre de archivo: nombre de materia sin espacios
            String nombreArchivo = carpeta + "/" + m.getNombre().replace(" ", "_") + ".txt";

            try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
                pw.println("MATERIA|" + m.toTexto());
                for (Tarea t : m.getTareas()) {
                    pw.println("TAREA|" + t.toTexto());
                }
            } catch (IOException e) {
                System.out.println("Error guardando materia: " + m.getNombre());
            }
        }
    }

    /**
     * Carga todas las materias y tareas desde la carpeta del estudiante.
     */
    public void cargarDatos() {
        String carpeta = "data/homework/" + email.replace("@", "_").replace(".", "_");
        File dir = new File(carpeta);

        if (!dir.exists()) return; // primera vez, sin datos

        File[] archivos = dir.listFiles((d, name) -> name.endsWith(".txt"));
        if (archivos == null) return;

        for (File archivo : archivos) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                Materia materia = null;
                String linea;

                while ((linea = br.readLine()) != null) {
                    if (linea.startsWith("MATERIA|")) {
                        materia = Materia.fromTexto(linea.substring(8));
                        materias.add(materia);

                    } else if (linea.startsWith("TAREA|") && materia != null) {
                        materia.agregarTarea(Tarea.fromTexto(linea.substring(6)));
                    }
                }
            } catch (IOException e) {
                System.out.println("Error cargando archivo: " + archivo.getName());
            }
        }
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
