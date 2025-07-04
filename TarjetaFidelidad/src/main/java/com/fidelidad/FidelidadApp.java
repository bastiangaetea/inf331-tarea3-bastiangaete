package com.fidelidad;

import java.util.*;
import java.time.LocalDate;

public class FidelidadApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ClienteRepo clientes = new ClienteRepo();
    private static final CompraRepo compras = new CompraRepo();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Gestión de Clientes");
            System.out.println("2. Registrar Compra");
            System.out.println("3. Mostrar Puntos/Nivel de Cliente");
            System.out.println("4. Listar Clientes Registrados");
            System.out.println("5. Salir");
            System.out.print("Elija opción: ");
            int op = scanner.nextInt();
            scanner.nextLine();
            switch (op) {
                case 1 -> menuClientes();
                case 2 -> registrarCompra();
                case 3 -> mostrarEstadoCliente();
                case 4 -> listarClientes();
                case 5 -> System.exit(0);
            }
        }
    }

    private static void menuClientes() {
    while (true) {
        System.out.println("Gestión de Clientes:");
        System.out.println("1. Agregar Cliente");
        System.out.println("2. Actualizar Cliente");
        System.out.println("3. Eliminar Cliente");
        System.out.println("4. Volver al menú principal");
        System.out.print("Elija opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1 -> {
                System.out.print("ID: "); String id = scanner.nextLine();
                if (clientes.obtener(id) != null) {
                    System.out.println("❌ Ya existe un cliente con ese ID");
                    break;
                }
                System.out.print("Nombre: "); String nombre = scanner.nextLine();
                System.out.print("Correo: "); String correo = scanner.nextLine();
                Cliente c = new Cliente(id, nombre, correo);
                clientes.agregar(c);
                System.out.println("✔ Cliente agregado");
            }
            case 2 -> {
                System.out.print("ID del cliente a actualizar: ");
                String id = scanner.nextLine();
                Cliente c = clientes.obtener(id);
                if (c == null) {
                    System.out.println("❌ Cliente no encontrado");
                    break;
                }
                System.out.print("Nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();
                System.out.print("Nuevo correo: ");
                String nuevoCorreo = scanner.nextLine();
                Cliente actualizado = new Cliente(id, nuevoNombre, nuevoCorreo);
                actualizado.agregarPuntos(c.getPuntos());
                actualizado.setStreakDias(c.getStreakDias());
                clientes.actualizar(actualizado);
                System.out.println("✔ Cliente actualizado");
            }
            case 3 -> {
                System.out.print("ID del cliente a eliminar: ");
                String id = scanner.nextLine();
                if (clientes.obtener(id) == null) {
                    System.out.println("❌ Cliente no encontrado");
                    break;
                }
                clientes.eliminar(id);
                System.out.println("✔ Cliente eliminado");
            }
            case 4 -> {
                return;
            }
            default -> System.out.println("❌ Opción no válida");
        }
    }
}

    private static void registrarCompra() {
        System.out.print("ID Cliente: "); String id = scanner.nextLine();
        Cliente c = clientes.obtener(id);
        if (c == null) {
            System.out.println("Cliente no encontrado"); return;
        }
        System.out.print("Monto: ");
        double monto = scanner.nextDouble(); scanner.nextLine();
        LocalDate hoy = LocalDate.now();
        Compra comp = new Compra(UUID.randomUUID().toString(), id, monto, hoy);
        compras.registrar(comp);

        int puntosBase = (int)(monto / 100);
        int puntosFinal = (int)(puntosBase * c.getNivel().multiplicador);

        if (compras.contarComprasHoy(id, hoy) == 3) puntosFinal += 10;

        c.agregarPuntos(puntosFinal);
        clientes.actualizar(c);
        System.out.println("✔ Compra registrada. Puntos ganados: " + puntosFinal);
    }

    private static void mostrarEstadoCliente() {
        System.out.print("ID Cliente: ");
        String id = scanner.nextLine();
        Cliente c = clientes.obtener(id);
        if (c == null) {
            System.out.println("Cliente no encontrado"); return;
        }
        System.out.println("Nombre: " + c.getNombre());
        System.out.println("Puntos: " + c.getPuntos());
        System.out.println("Nivel: " + c.getNivel());
    }

    private static void listarClientes() {
        System.out.println("\nClientes registrados:");
        for (Cliente c : clientes.listar()) {
            System.out.println("- ID: " + c.getId() + ", Nombre: " + c.getNombre() + ", Correo: " + c.getCorreo() + ", Puntos: " + c.getPuntos() + ", Nivel: " + c.getNivel());
        }
    }
}
