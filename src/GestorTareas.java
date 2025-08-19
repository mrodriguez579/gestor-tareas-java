import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

public class GestorTareas {
    private ArrayList<Tarea> tareas;

    public GestorTareas() {
        tareas = new ArrayList<>();
    }

    public void agregarTarea(String descripcion) {
        tareas.add(new Tarea(descripcion));
        System.out.println("✅ Tarea agregada.");
    }

    public void listarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("📭 No hay tareas.");
            return;
        }
        for (int i = 0; i < tareas.size(); i++) {
            System.out.println((i + 1) + ". " + tareas.get(i));
        }
    }

    public void completarTarea(int indice) {
        if (indice < 0 || indice >= tareas.size()) {
            System.out.println("❌ Índice inválido.");
            return;
        }
        tareas.get(indice).marcarCompletada();
        System.out.println("✅ Tarea marcada como completada.");
    }

    public void eliminarCompletadas() {
        Iterator<Tarea> it = tareas.iterator();
        while (it.hasNext()) {
            if (it.next().isCompletada()) {
                it.remove();
            }
        }
        System.out.println("🗑️ Tareas completadas eliminadas.");
    }

    // Extra: guardar tareas en archivo
    public void guardarEnArchivo(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Tarea tarea : tareas) {
                writer.write(tarea.getDescripcion() + ";" + tarea.isCompletada());
                writer.newLine();
            }
            System.out.println("💾 Tareas guardadas en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("❌ Error al guardar el archivo.");
        }
    }

    // Extra: cargar tareas desde archivo
    public void cargarDeArchivo(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    Tarea tarea = new Tarea(partes[0]);
                    if (Boolean.parseBoolean(partes[1])) {
                        tarea.marcarCompletada();
                    }
                    tareas.add(tarea);
                }
            }
            System.out.println("📂 Tareas cargadas desde " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("⚠️ No se encontraron tareas guardadas.");
        }
    }
}
