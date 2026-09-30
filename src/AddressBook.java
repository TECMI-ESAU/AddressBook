import java.io.*;
import java.util.*;

// Agenda telefonica con persistencia en archivo CSV
public class AddressBook {

    private static final String SEPARATOR = ",";

    // numero -> nombre
    private Map<String, String> contacts = new HashMap<>();

    // ruta del archivo CSV
    private final String filePath;

    public AddressBook(String filePath) {
        this.filePath = filePath;
    }

    // Carga los contactos del archivo. Si no existe, la agenda inicia vacia.
    public void load() {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(SEPARATOR, 2);
                if (parts.length == 2) {
                    String phone = parts[0].trim();
                    String name  = parts[1].trim();
                    contacts.put(phone, name);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Guarda todos los contactos en el archivo CSV.
    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                writer.write(entry.getKey() + SEPARATOR + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    // Muestra todos los contactos en consola.
    public void list() {
        if (contacts.isEmpty()) {
            System.out.println("La agenda está vacía.");
            return;
        }

        System.out.println("\nContactos:");
        contacts.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));
        System.out.println();
    }

    // Agrega un nuevo contacto.
    public void create(String phone, String name) {
        contacts.put(phone, name);
        save();
        System.out.println("Contacto guardado: " + phone + " : " + name);
    }

    // Elimina el contacto con el numero indicado.
    public void delete(String phone) {
        if (contacts.containsKey(phone)) {
            contacts.remove(phone);
            save();
            System.out.println("Contacto eliminado: " + phone);
        } else {
            System.out.println("No se encontró un contacto con el número: " + phone);
        }
    }
}
