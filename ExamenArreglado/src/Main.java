import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Electrodomestico> electrodomesticos = new ArrayList<>();
    private static ArrayList<Consumo> consumos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Seleccion una opcion");
            System.out.println("1) Crear Usuario");
            System.out.println("2) Ingresar al programa");
            int opcion = scanner.nextInt();
            if (opcion == 2) {
                System.out.println("Ingrese cedula: ");
                String cedula = scanner.next();
                Usuario usuario = buscarUsuario(cedula);
                if (usuario != null) {
                    while (true) {
                        System.out.println("1) Ingresar Vivienda");
                        System.out.println("2) Registrar Nuevo Electrodomestico");
                        System.out.println("3) Ingresar Electrodomestico a una vivienda");
                        System.out.println("4) Ver Datos");
                        System.out.println("5) Generar Factura");
                        System.out.println("6) Eliminar vivienda");
                        System.out.println("7) Modificar vivienda Factura");
                        System.out.println("8) Salir");
                        int opcionUsuario = scanner.nextInt();
                        switch (opcionUsuario) {
                            case 1:
                                ingresarVivienda(usuario, scanner);
                                break;
                            case 2:
                                ingresarNuevoElectrodomestico(scanner);
                                break;
                            case 3:
                                ingresarElectrodomestico(usuario, scanner);
                                break;
                            case 4:
                                imprimirDatosUsuario(usuario);
                                break;
                            case 5:
                                imprimirFactura(usuario);
                                break;
                            case 6:
                                eliminarVivienda(usuario, scanner);
                                break;
                            case 7:
                                modificarVivienda(usuario, scanner);
                                break;
                            case 8:
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Opción no válida");
                        }
                    }
                } else {
                    System.out.println("Usuario no encontrado");
                }
            } else if (opcion == 1) {
                crearUsuario(scanner);
            } else {
                System.out.println("Opción no válida");
            }
        }
    }

    private static Usuario buscarUsuario(String cedula){
        for (Usuario usuario : usuarios) {
            if (usuario.getCedula().equals(cedula)) {
                System.out.println("Vienvenido " + usuario.getNombre() + " " + usuario.getApellido());
                return usuario;
            }
        }
        return null;
    }
    private static void crearUsuario(Scanner scanner){
        System.out.println("Ingrese nombre: ");
        String nombre = scanner.next();
        System.out.println("Ingrese apellido: ");
        String apellido = scanner.next();
        System.out.println("Ingrese cedula: ");
        String cedula = scanner.next();

        if (nombre.matches(".\\d.") || !nombre.matches("[a-zA-Z]+")) {
            System.out.println("El nombre no puede contener números ni símbolos.");
            return;
        }
        if (apellido.matches(".\\d.") || !apellido.matches("[a-zA-Z]+")) {
            System.out.println("El apellido no puede contener números ni símbolos.");
            return;
        }
        if (!esCedulaValida(cedula)) {
            System.out.println("La cédula no es válida.");
            return;

        }
        for (Usuario user : usuarios) {
            if (user.getCedula().equals(cedula)) {
                System.out.println("Ya existe un usuario con esa cédula.");
                return;
            }
        }
        if (cedula.length() != 10) {
            System.out.println("La cédula debe tener 10 dígitos.");
            return;
        }
        Usuario nuevoUsuario = new Usuario(nombre, apellido, cedula);
        usuarios.add(nuevoUsuario);
        System.out.println("Usuario creado existosamente");
    }
    private static boolean esCedulaValida(String cedula) {
        for (int i = 0; i < cedula.length(); i++) {
            if (!Character.isDigit(cedula.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    private static void ingresarVivienda(Usuario usuario, Scanner scanner) {
        System.out.println("!Ingrese todos los datos sin ningun espacio¡");
        System.out.println("Ingrese sector: ");
        String sector = scanner.next();
        System.out.println("Ingrese calle principal: ");
        String callePrincipal = scanner.next();
        scanner.nextLine();
        System.out.println("Ingrese calle secundaria: ");
        String calleSecundaria = scanner.next();
        System.out.println("Ingrese código postal: ");
        String codigoPostal = scanner.next();
        Vivienda nuevaVivienda = new Vivienda(sector, callePrincipal, calleSecundaria, codigoPostal);
        usuario.agregarVivienda(nuevaVivienda);
        System.out.println("Vivienda Registrada Exitosamente");
    }
    private static void ingresarNuevoElectrodomestico(Scanner scanner) {
        System.out.println("Ingrese nombre del electrodomestico: ");
        String nombre = scanner.next();
        if (existeElectrodomestico(nombre)) {
            System.out.println("Ya existe un electrodomestico con ese nombre. Ingrese uno diferente.");
            return;
        }
        System.out.println("Ingrese consumo en watts: ");
        int consumoEnWatts = scanner.nextInt();

        Electrodomestico nuevoElectrodomestico = new Electrodomestico(nombre, consumoEnWatts);
        electrodomesticos.add(nuevoElectrodomestico);
        System.out.println("Electrodomestico registrado exitosamente");
    }
    private static boolean existeElectrodomestico(String nombre) {
        for (Electrodomestico electrodomestico : electrodomesticos) {
            if (electrodomestico.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    private static void ingresarElectrodomestico(Usuario usuario, Scanner scanner) {
        System.out.println("Seleccione electrodomestico:");
        for (int i = 0; i < electrodomesticos.size(); i++) {
            System.out.println((i + 1) + ") " + electrodomesticos.get(i).getNombre());
        }
        int opcion = scanner.nextInt();
        if (opcion > 0 && opcion <= electrodomesticos.size()) {
            Electrodomestico electrodomestico = electrodomesticos.get(opcion - 1);
            System.out.println("Ingrese vivienda:");
            for (int i = 0; i < usuario.getViviendas().size(); i++) {
                System.out.println((i + 1) + ") " + usuario.getViviendas().get(i).getSector());
            }
            int opcionVivienda = scanner.nextInt();
            if (opcionVivienda > 0 && opcionVivienda <= usuario.getViviendas().size()) {
                Vivienda vivienda = usuario.getViviendas().get(opcionVivienda - 1);
                System.out.println("Ingrese cantidad de electrodomesticos:");
                int cantidad = scanner.nextInt();
                if (cantidad < 0) {
                    System.out.println("La cantidad no puede ser un número negativo. Ingrese nuevamente.");
                    return;
                }
                System.out.println("Ingrese tiempo de uso en horas:");
                double tiempo;
                try {
                    tiempo = Double.parseDouble(scanner.next());
                    if (tiempo < 0) {
                        System.out.println("El tiempo no puede ser un número negativo. Ingrese nuevamente.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("El tiempo debe ser un número. Ingrese nuevamente.");
                    return;
                }
                Consumo nuevoConsumo = new Consumo(electrodomestico, cantidad, tiempo);
                vivienda.agregarConsumo(nuevoConsumo);
                System.out.println("Electrodomestico agregado exitosamente a la vivienda.");
            } else {
                System.out.println("Opción de vivienda no válida");
            }
        } else {
            System.out.println("Opción de electrodomestico no válida");
        }
    }


    private static void imprimirDatosUsuario(Usuario usuario) {
        System.out.println("Nombre: " + usuario.getNombre() + " " + usuario.getApellido());
        System.out.println("Cedula: " + usuario.getCedula());
        System.out.println("Viviendas");
        for (Vivienda vivienda : usuario.getViviendas()) {
            System.out.println("Sector: " + vivienda.getSector());
            System.out.println("Calles: " + vivienda.getCallePrincipal() + " y " +vivienda.getCalleSecundaria());
            System.out.println("Código Postal: " + vivienda.getCodigoPostal());
            System.out.println("Electrodomesticos");
            for (Consumo consumo : vivienda.getConsumos()) {
                Electrodomestico electrodomestico = consumo.getElectrodomestico();
                if (electrodomestico.getNombre().equals(electrodomestico.getNombre())) {
                    System.out.println("- " + electrodomestico.getNombre());
                }
            }
            System.out.println("-----");
        }
    }
    private static void imprimirFactura(Usuario usuario) {
        Factura factura = usuario.generarFactura();
        factura.imprimirFactura();
    }

    private static void eliminarVivienda(Usuario usuario, Scanner scanner) {
        System.out.println("Seleccione la vivienda a eliminar:");
        for (int i = 0; i < usuario.getViviendas().size(); i++) {
            System.out.println((i + 1) + ") " + usuario.getViviendas().get(i).getSector());
        }
        int opcionVivienda = scanner.nextInt();
        if (opcionVivienda > 0 && opcionVivienda <= usuario.getViviendas().size()) {
            usuario.getViviendas().remove(opcionVivienda - 1);
            System.out.println("Vivienda eliminada exitosamente");
        } else {
            System.out.println("Opción de vivienda no válida");
        }
    }

    private static void modificarVivienda(Usuario usuario, Scanner scanner) {
        System.out.println("Seleccione la vivienda a modificar:");
        for (int i = 0; i < usuario.getViviendas().size(); i++) {
            System.out.println((i + 1) + ") " + usuario.getViviendas().get(i).getSector());
        }
        int opcionVivienda = scanner.nextInt();
        if (opcionVivienda > 0 && opcionVivienda <= usuario.getViviendas().size()) {
            Vivienda vivienda = usuario.getViviendas().get(opcionVivienda - 1);
            System.out.println("Ingrese nuevos datos para la vivienda:");
            ingresarViviendaDatos(vivienda, scanner);
            System.out.println("Vivienda modificada exitosamente");
        } else {
            System.out.println("Opción de vivienda no válida");
        }
    }

    private static void ingresarViviendaDatos(Vivienda vivienda, Scanner scanner) {
        System.out.println("!Ingrese todos los datos sin ningún espacio¡");
        System.out.println("Ingrese sector: ");
        vivienda.setSector(scanner.next());
        System.out.println("Ingrese calle principal: ");
        vivienda.setCallePrincipal(scanner.next());
        scanner.nextLine();
        System.out.println("Ingrese calle secundaria: ");
        vivienda.setCalleSecundaria(scanner.next());
        System.out.println("Ingrese código postal: ");
        vivienda.setCodigoPostal(scanner.next());
    }
}
