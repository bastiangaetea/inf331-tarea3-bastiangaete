package com.fidelidad;

import java.time.LocalDate;

public class Compra {
    private String id;
    private String idCliente;
    private double monto;
    private LocalDate fecha;

    public Compra(String id, String idCliente, double monto, LocalDate fecha) {
        this.id = id;
        this.idCliente = idCliente;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getIdCliente() { return idCliente; }
    public double getMonto() { return monto; }
    public LocalDate getFecha() { return fecha; }
}