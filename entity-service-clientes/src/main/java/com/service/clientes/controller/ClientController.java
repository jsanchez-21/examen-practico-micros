package com.service.clientes.controller;

import com.service.clientes.model.Cliente;
import com.service.clientes.model.ClientsList;
import com.service.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes/v1")
    public ClientsList retrieveClients(){
        return clienteService.readClients();
    }

    @PostMapping("/clientes/v1")
    public void createClient(@RequestBody Cliente cliente){
        clienteService.createClient(cliente);
    }

    @PutMapping("/clientes/v1/{idCliente}")
    public void updateClient(@PathVariable int idCliente, @RequestBody Cliente cliente){
        cliente.setId(idCliente);
        clienteService.updateClient(cliente);
    }

    @DeleteMapping("/clientes/v1/{idCliente}")
    public void removeClient(@PathVariable int idCliente){
        clienteService.removeClient(idCliente);
    }
}
