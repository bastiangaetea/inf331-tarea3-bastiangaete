package com.fidelidad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompraRepoTest {

    private CompraRepo repo;
    private LocalDate hoy;

    @BeforeEach
    public void setUp() {
        repo = new CompraRepo();
        hoy = LocalDate.now();
    }

    @Test
    public void testRegistrarYListarComprasPorCliente() {
        Compra c1 = new Compra("c1", "cliente1", 150.0, hoy);
        Compra c2 = new Compra("c2", "cliente1", 200.0, hoy);
        Compra c3 = new Compra("c3", "cliente2", 300.0, hoy);

        repo.registrar(c1);
        repo.registrar(c2);
        repo.registrar(c3);

        List<Compra> comprasCliente1 = repo.listarPorCliente("cliente1");
        assertEquals(2, comprasCliente1.size());

        List<Compra> comprasCliente2 = repo.listarPorCliente("cliente2");
        assertEquals(1, comprasCliente2.size());
    }

    @Test
    public void testContarComprasHoy() {
        repo.registrar(new Compra("c1", "clienteX", 100.0, hoy));
        repo.registrar(new Compra("c2", "clienteX", 150.0, hoy));
        repo.registrar(new Compra("c3", "clienteX", 200.0, hoy.minusDays(1))); // no cuenta

        int comprasHoy = repo.contarComprasHoy("clienteX", hoy);
        assertEquals(2, comprasHoy);
    }

    @Test
    public void testContarComprasHoySinCompras() {
        int comprasHoy = repo.contarComprasHoy("nuevoCliente", hoy);
        assertEquals(0, comprasHoy);
    }
} 
