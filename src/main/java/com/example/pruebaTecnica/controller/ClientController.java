package com.example.pruebaTecnica.controller;

import com.example.pruebaTecnica.dto.ClientDto;
import com.example.pruebaTecnica.dto.ClientDtoResponse;
import com.example.pruebaTecnica.entity.ClientEntity;
import com.example.pruebaTecnica.exception.ClientNotFoundException;
import com.example.pruebaTecnica.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/clientes")
@RequiredArgsConstructor

public class ClientController {

    private final ClientService clientService;

    @GetMapping("/findAll")
    public List<ClientEntity> findAll(){
        return clientService.findAll();
    }

    @GetMapping("/findById/{id}")
    public ClientDtoResponse findById(@PathVariable Long id) throws ClientNotFoundException {
        return clientService.findById(id);
    }

    @PostMapping("/save")
    public Object save(@RequestBody @Validated ClientDto clientDataDto) {
        return clientService.save(clientDataDto);
    }

    @PutMapping("/update/{id}")
    public ClientDto update(@PathVariable Long id, @RequestBody ClientDto clientDataDto) throws ClientNotFoundException {
        return clientService.update(id, clientDataDto);
    }

    @GetMapping("/findByName/{nombre}")
    public ClientDtoResponse findClienteByName(@PathVariable String nombre) throws ClientNotFoundException {
        return clientService.findClienteByName(nombre);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return clientService.delete(id);
    }

}
