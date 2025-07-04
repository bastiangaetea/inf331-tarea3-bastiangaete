package com.fidelidad;

import java.util.*;

public class ClienteRepo {
    private Map<String, Cliente> clientes = new HashMap<>();

    public void agregar(Cliente c) { clientes.put(c.getId(), c); }
    public Cliente obtener(String id) { return clientes.get(id); }
    public Collection<Cliente> listar() { return clientes.values(); }
    public void eliminar(String id) { clientes.remove(id); }
    public void actualizar(Cliente c) { clientes.put(c.getId(), c); }
}