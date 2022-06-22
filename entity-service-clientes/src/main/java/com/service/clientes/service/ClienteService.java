package com.service.clientes.service;

import com.service.clientes.model.Cliente;
import com.service.clientes.model.ClientsList;

import java.util.List;

public interface ClienteService {

    ClientsList readClients();
    boolean createClient(Cliente cliente);
    boolean updateClient(Cliente cliente);
    boolean removeClient(int IdCliente);
}
