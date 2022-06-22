package com.service.clientes.model;

import java.util.List;


public class ClientsList {
    private List<Cliente> clientes;

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "ClientsList{" +
                "clientes=" + clientes +
                '}';
    }
}
