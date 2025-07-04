package com.fidelidad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FidelidadAppIntegrationTest {

    private ClienteRepo clienteRepo;
    private CompraRepo compraRepo;

    @BeforeEach
    public void setUp() {
        clienteRepo = new ClienteRepo();
        compraRepo = new CompraRepo();
    }

    @Test
    public void testCompraCompletaConBonificacionYNivel() {
        // Crear cliente
        Cliente cliente = new Cliente("001", "Laura", "laura@example.com");
        clienteRepo.agregar(cliente);

        LocalDate hoy = LocalDate.now();

        // Registrar 3 compras el mismo día
        Compra c1 = new Compra("c1", "001", 200.0, hoy); // 2 puntos base
        Compra c2 = new Compra("c2", "001", 150.0, hoy); // 1 punto base
        Compra c3 = new Compra("c3", "001", 300.0, hoy); // 3 puntos base

        compraRepo.registrar(c1);
        compraRepo.registrar(c2);
        compraRepo.registrar(c3);

        int puntosTotales = 0;

        for (Compra compra : compraRepo.listarPorCliente("001")) {
            int base = (int) (compra.getMonto() / 100);
            double multiplicador = cliente.getNivel().multiplicador;
            puntosTotales += (int) (base * multiplicador);
        }

        // Bonificación por 3 compras el mismo día
        int comprasHoy = compraRepo.contarComprasHoy("001", hoy);
        if (comprasHoy == 3) {
            puntosTotales += 10;
        }

        // Agregar puntos y recalcular nivel
        cliente.agregarPuntos(puntosTotales);
        clienteRepo.actualizar(cliente);

        // Verificar
        Cliente actualizado = clienteRepo.obtener("001");
        assertEquals(6 + 10, actualizado.getPuntos()); // (2+1+3)*1 + 10 bonus
        assertEquals(Nivel.BRONCE, actualizado.getNivel());

        // Simular más puntos para subir de nivel
        actualizado.agregarPuntos(600);
        assertEquals(Nivel.PLATA, actualizado.getNivel());
    }
} 
