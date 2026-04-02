package SistemaDeGestionAcademica;

import java.time.LocalDate;
import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Estudiante estudiante;
    static List<Estudiante> usuarios = new ArrayList<>();

    public static void main(String[] args) {

        iniciarSistema();

        int op;
        do {
            System.out.println(" MENÚ PRINCIPAL");
            System.out.println("1. Materias");
            System.out.println("2. Tareas");
            System.out.println("3. Estadísticas");
            System.out.println("4. Alertas");
            System.out.println("0. Salir");

            op = sc.nextInt();

            switch (op) {
                case 1 -> menuMaterias();
                case 2 -> menuTareas();
                case 3 -> menuEstadisticas();
                case 4 -> menuAlertas();
                case 0 -> {System.out.println(" Estas saliendo del sistema ... Vuelve pronto !!!  ");}
                
                
            }

        } while (op != 0);
    }

    // REGISTRO
    public static void iniciarSistema() {

        int op;

        do {
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");

            op = sc.nextInt();
            sc.nextLine();

            if (op == 1) registrarUsuario();
            else if (op == 2) iniciarSesion();

        } while (estudiante == null);
    }

    public static void registrarUsuario() {

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Edad: ");
        int edad = sc.nextInt();
        sc.nextLine();

        System.out.print("Tipo (colegio/universidad): ");
        String tipo = sc.nextLine();

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        Estudiante nuevo = new Estudiante(usuarios.size() + 1,nombre,apellido,edad,tipo,correo,pass );
        usuarios.add(nuevo);
        System.out.println("Registro exitoso");
    }

    public static void iniciarSesion() {

        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        for (Estudiante e : usuarios) {
            if (e.getEmail().equals(correo) && e.getContraseña().equals(pass)) {

                estudiante = e;
                System.out.println("Bienvenido " + e.getNombreCompleto());
                return;
            }
        }

        System.out.println(" Datos incorrectos, intente de nuevo o Registrese ");
    }

    // MATERIAS
    public static void menuMaterias() {

        int op;
        do {
            System.out.println(" MATERIAS ");
            System.out.println("1. Crear");
            System.out.println("2. Ver");
            System.out.println("0. Volver");

            op = sc.nextInt();
            sc.nextLine();

            if (op == 1) {

                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Profesor: ");
                String profesor = sc.nextLine();

                System.out.print("Descripción: ");
                String desc = sc.nextLine();

                System.out.print("Importancia: ");
                String importancia = sc.nextLine();

                estudiante.agregarMateria(new Materia(estudiante.getMaterias().size() + 1,nombre,desc,profesor,importancia));
            }

            if (op == 2) estudiante.verMaterias();

        } while (op != 0);
    }

    //  TAREAS
    public static void menuTareas() {

        if (estudiante.getMaterias().isEmpty()) {
            System.out.println("No hay materias");
            return;
        }

        estudiante.verMaterias();
        int mat = sc.nextInt() - 1;

        Materia m = estudiante.getMaterias().get(mat);

        int op;
        do {
            System.out.println(" TAREAS ");
            System.out.println("1. Crear");
            System.out.println("2. Ver");
            System.out.println("3. Marcar");
            System.out.println("4. Editar");
            System.out.println("5. Eliminar");
            System.out.println("6. Filtrar");
            System.out.println("7. Estado");
            System.out.println("0. Volver");

            op = sc.nextInt();

            switch (op) {

                case 1 -> {
                    sc.nextLine();

                    System.out.print("Título: ");
                    String t = sc.nextLine();

                    System.out.print("Descripción: ");
                    String d = sc.nextLine();

                    System.out.print("Tipo: ");
                    String tipo = sc.nextLine();

                    System.out.print("Tema: ");
                    String tema = sc.nextLine();

                    System.out.print("Tiempo (h): ");
                    int tiempo = sc.nextInt();

                    System.out.print("Días para entrega: ");
                    int dias = sc.nextInt();

                    System.out.println("1.ALTA 2.MEDIA 3.BAJA");
                    int p = sc.nextInt();

                    Prioridad pr = (p == 1) ? Prioridad.ALTA :
                            (p == 2) ? Prioridad.MEDIA : Prioridad.BAJA;

                    m.agregarTarea(new Tarea(m.getTareas().size() + 1,t, d, tipo, tema,tiempo,LocalDate.now().plusDays(dias),pr));
                }

                case 2 -> m.mostrarTareas();
                case 3 -> {
                    m.mostrarTareas();
                    int i = sc.nextInt() - 1;
                    m.getTareas().get(i).marcarComoHecha();
                }

                case 4 -> {
                    m.mostrarTareas();
                    int i = sc.nextInt() - 1;

                    sc.nextLine();
                    System.out.print("Nuevo título: ");
                    String t = sc.nextLine();

                    System.out.print("Nueva desc: ");
                    String d = sc.nextLine();

                    System.out.println("1.ALTA 2.MEDIA 3.BAJA");
                    int p = sc.nextInt();

                    Prioridad pr = (p == 1) ? Prioridad.ALTA :
                            (p == 2) ? Prioridad.MEDIA : Prioridad.BAJA;

                    m.editarTarea(i, t, d, pr);
                }

                case 5 -> {
                    m.mostrarTareas();
                    int i = sc.nextInt() - 1;
                    m.eliminarTarea(i);
                }

                case 6 -> {
                    System.out.println("1.ALTA 2.MEDIA 3.BAJA");
                    int p = sc.nextInt();

                    Prioridad pr = (p == 1) ? Prioridad.ALTA :
                            (p == 2) ? Prioridad.MEDIA : Prioridad.BAJA;

                    m.filtrarPorPrioridad(pr);
                }

                case 7 -> m.mostrarEstadoMateria();
            }

        } while (op != 0);
    }

    public static void menuEstadisticas() {

        int op;
        do {
            System.out.println(" ESTADÍSTICAS ");
            System.out.println("1. Progreso");
            System.out.println("2. Ranking");
            System.out.println("0. Volver");

            op = sc.nextInt();

            if (op == 1)
                Estadistica.mostrarProgreso(estudiante.getMaterias());

            if (op == 2)
                Estadistica.rankingProductividad(estudiante.getMaterias());

        } while (op != 0);
    }

    public static void menuAlertas() {
        Alerta.generarAlertas(estudiante.getMaterias());
    }
}