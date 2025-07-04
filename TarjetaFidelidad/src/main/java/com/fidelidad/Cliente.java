package com.fidelidad;

public class Cliente {
    private String id;
    private String nombre;
    private String correo;
    private int puntos = 0;
    private Nivel nivel = Nivel.BRONCE;
    private int streakDias = 0;

    public Cliente(String id, String nombre, String correo) {
        if (!correo.contains("@")) throw new IllegalArgumentException("Correo no válido");
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    public void agregarPuntos(int puntosGanados) {
        this.puntos += puntosGanados;
        this.nivel = Nivel.calcularNivel(this.puntos);
    }

    // Getters y setters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public int getPuntos() { return puntos; }
    public Nivel getNivel() { return nivel; }
    public int getStreakDias() { return streakDias; }

    public void setStreakDias(int streakDias) { this.streakDias = streakDias; }
}