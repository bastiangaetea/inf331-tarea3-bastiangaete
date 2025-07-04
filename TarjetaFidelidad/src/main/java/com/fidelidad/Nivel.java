package com.fidelidad;

public enum Nivel {
    BRONCE(0, 499, 1.0),
    PLATA(500, 1499, 1.2),
    ORO(1500, 2999, 1.5),
    PLATINO(3000, Integer.MAX_VALUE, 2.0);

    public final int min;
    public final int max;
    public final double multiplicador;

    Nivel(int min, int max, double multiplicador) {
        this.min = min;
        this.max = max;
        this.multiplicador = multiplicador;
    }

    public static Nivel calcularNivel(int puntos) {
        for (Nivel n : values()) {
            if (puntos >= n.min && puntos <= n.max) return n;
        }
        return BRONCE;
    }
}