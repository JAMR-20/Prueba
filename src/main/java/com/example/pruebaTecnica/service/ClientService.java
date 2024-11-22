package com.example.pruebaTecnica.service;

import com.example.pruebaTecnica.dto.ClientDto;
import com.example.pruebaTecnica.dto.ClientDtoResponse;
import com.example.pruebaTecnica.entity.ClientEntity;
import com.example.pruebaTecnica.exception.ClientNotFoundException;

import java.util.List;

public interface ClientService {
    List<ClientEntity> findAll();

    ClientDtoResponse findById(Long id) throws ClientNotFoundException;

    Object save (ClientDto clientDataDto);

    String delete (Long id) ;

    ClientDto update (Long id, ClientDto clientDataDto) throws ClientNotFoundException;

    ClientDtoResponse findClienteByName (String nombre) throws ClientNotFoundException;
}
