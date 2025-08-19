import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorTareas gestor = new GestorTareas();
        Scanner sc = new Scanner(System.in);

        gestor.cargarDeArchivo("tareas.txt");

        int opcion;
        do {
            System.out.println("\n--- Menú Gestor de Tareas ---");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Listar tareas");
            System.out.println("3. Marcar tarea como completada");
            System.out.println("4. Eliminar tareas completadas");
            System.out.println("5. Guardar y salir");
            System.out.print("Elige una opción: ");

            while (!sc.hasNextInt()) {
                System.out.print("Ingresa un número válido: ");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Descripción: ");
                    String descripcion = sc.nextLine();
                    gestor.agregarTarea(descripcion);
                    break;
                case 2:
                    gestor.listarTareas();
                    break;
                case 3:
                    gestor.listarTareas();
                    System.out.print("Número de tarea a completar: ");
                    int indice = sc.nextInt() - 1;
                    gestor.completarTarea(indice);
                    break;
                case 4:
                    gestor.eliminarCompletadas();
                    break;
                case 5:
                    gestor.guardarEnArchivo("tareas.txt");
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);

        sc.close();
    }
}
