import java.util.Scanner;

// Punto de entrada. Muestra el menu y atiende las acciones del usuario.
public class Main {

    private static final String FILE_PATH = "contacts.csv";

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook(FILE_PATH);
        addressBook.load();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Selecciona una opcion: ");
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    addressBook.list();
                    break;

                case "2":
                    System.out.print("Nombre del contacto: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Numero telefonico:   ");
                    String phone = scanner.nextLine().trim();
                    if (name.isEmpty() || phone.isEmpty()) {
                        System.out.println("El nombre y el numero no pueden estar vacios.");
                    } else {
                        addressBook.create(phone, name);
                    }
                    break;

                case "3":
                    System.out.print("Numero telefonico a eliminar: ");
                    String phoneToDelete = scanner.nextLine().trim();
                    addressBook.delete(phoneToDelete);
                    break;

                case "4":
                    running = false;
                    System.out.println("Hasta luego!");
                    break;

                default:
                    System.out.println("Opcion no valida. Intenta de nuevo.");
            }
        }

        scanner.close();
    }

    // Imprime el menu de opciones.
    private static void printMenu() {
        System.out.println("");
        System.out.println("      AGENDA TELEFONICA     ");
        System.out.println("");
        System.out.println("  1. Listar contactos");
        System.out.println("  2. Crear contacto");
        System.out.println("  3. Eliminar contacto");
        System.out.println("  4. Salir");
        System.out.println("");
    }
}
