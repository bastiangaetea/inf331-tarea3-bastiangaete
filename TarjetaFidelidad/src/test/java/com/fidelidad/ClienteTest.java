package com.fidelidad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void testCrearClienteConCorreoValido() {
        Cliente c = new Cliente("1", "Juan", "juan@mail.com");
        assertEquals("Juan", c.getNombre());
        assertEquals("juan@mail.com", c.getCorreo());
        assertEquals(0, c.getPuntos());
        assertEquals(Nivel.BRONCE, c.getNivel());
    }

    @Test
    public void testCorreoInvalidoLanzaExcepcion() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("2", "Ana", "correo_invalido");
        });
        assertTrue(e.getMessage().contains("Correo"));
    }

    @Test
    public void testAgregarPuntosActualizaNivelCorrectamente() {
        Cliente c = new Cliente("3", "Luis", "luis@mail.com");
        c.agregarPuntos(600); // Debe ser nivel PLATA
        assertEquals(Nivel.PLATA, c.getNivel());

        c.agregarPuntos(1000); // Total 1600 → ORO
        assertEquals(Nivel.ORO, c.getNivel());

        c.agregarPuntos(2000); // Total 3600 → PLATINO
        assertEquals(Nivel.PLATINO, c.getNivel());
    }

    @Test
    public void testStreakSetterYGetter() {
        Cliente c = new Cliente("4", "Sofía", "sofia@mail.com");
        c.setStreakDias(3);
        assertEquals(3, c.getStreakDias());
    }
} 
