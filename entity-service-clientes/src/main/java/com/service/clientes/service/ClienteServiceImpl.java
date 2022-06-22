package com.service.clientes.service;

import com.google.gson.Gson;
import com.service.clientes.commons.Utils;
import com.service.clientes.model.Cliente;
import com.service.clientes.model.ClientsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private Utils utils;


    @Override
    public ClientsList readClients() {
        ClientsList clientsList;
        try {
            String json = utils.readJson();
            clientsList = new Gson().fromJson(json, ClientsList.class);

        } catch (IOException e) {
            throw new RuntimeException("ocurrion un error al leer json");
        }

        return clientsList;
    }

    @Override
    public boolean createClient(Cliente cliente) {
        ClientsList clientsList = readClients();
        int currentId = readLastId(clientsList);

        cliente.setId(++currentId);
        clientsList.getClientes().add(cliente);

        try {
            utils.writeJson(clientsList);
        } catch (IOException e) {
            throw new RuntimeException("Ocurrio un error al crear el cliente");
        }

        return true;
    }

    @Override
    public boolean updateClient(Cliente cliente) {
        ClientsList clientsList = readClients();

        Optional<Cliente> optionalCliente = findById(clientsList, cliente.getId());
        try {
            if (optionalCliente.isPresent()) {
                Cliente clienteUpdate = optionalCliente.get();
                clienteUpdate.setCorreo(cliente.getCorreo());
                clienteUpdate.setNombre(cliente.getNombre());

                utils.writeJson(clientsList);

            } else {
                throw new RuntimeException("Cliente no encontrado");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al actualizar el cliente");
        }

        return true;
    }

    @Override
    public boolean removeClient(int idCliente) {
        ClientsList clientsList = readClients();

        boolean isRemoved = clientsList.getClientes()
                .removeIf(c -> c.getId() == idCliente);

        if (!isRemoved){
            throw new RuntimeException("no se encuentra el id para ser eiliminado");
        }

        try {
            utils.writeJson(clientsList);
        } catch (IOException e) {
            throw new RuntimeException("Ocurrio un error al crear el cliente");
        }

        return false;
    }

    private int readLastId(ClientsList clientsList) {
        return clientsList.getClientes().get(clientsList.getClientes().size() - 1).getId();
    }

    private Optional<Cliente> findById(ClientsList clientsList, int id) {
        return clientsList.getClientes()
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }
}
