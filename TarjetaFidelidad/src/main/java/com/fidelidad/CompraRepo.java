package com.fidelidad;

import java.util.*;
import java.time.LocalDate;

public class CompraRepo {
    private List<Compra> compras = new ArrayList<>();

    public void registrar(Compra c) { compras.add(c); }
    public List<Compra> listarPorCliente(String idCliente) {
        List<Compra> result = new ArrayList<>();
        for (Compra c : compras) {
            if (c.getIdCliente().equals(idCliente)) result.add(c);
        }
        return result;
    }

    public int contarComprasHoy(String idCliente, LocalDate hoy) {
        int count = 0;
        for (Compra c : compras) {
            if (c.getIdCliente().equals(idCliente) && c.getFecha().equals(hoy)) {
                count++;
            }
        }
        return count;
    }
}