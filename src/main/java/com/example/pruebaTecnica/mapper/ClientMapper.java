package com.example.pruebaTecnica.mapper;

import com.example.pruebaTecnica.dto.ClientDto;
import com.example.pruebaTecnica.dto.ClientDtoResponse;
import com.example.pruebaTecnica.dto.ClientDtoResponseData;
import com.example.pruebaTecnica.entity.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public static ClientEntity clienteDtoToEntity(ClientDto clienteDto){
        ClientEntity clienteEntity = new ClientEntity();

        clienteEntity.setApellido(clienteDto.getApellido());
        clienteEntity.setEmail(clienteDto.getEmail());
        clienteEntity.setFechaNacimiento(clienteDto.getFechaNacimiento());
        clienteEntity.setNumeroIdentificacion(clienteDto.getNumeroIdentificacion());
        clienteEntity.setTipoIdentificacion(clienteDto.getTipoIdentificacion());
        clienteEntity.setNombre(clienteDto.getNombre());

        return  clienteEntity;
    }

    public static ClientDtoResponse clienteEntityToClienClienteDtoResponse(String message, ClientEntity cliente){
        return ClientDtoResponse.
                builder()
                .code("200")
                .message(message)
                .data(
                        ClientDtoResponseData
                                .builder()
                                .nombre(cliente.getNombre())
                                .apellido(cliente.getApellido())
                                .numeroIdentificacion(cliente.getNumeroIdentificacion())
                                .email(cliente.getEmail())
                                .build()
                )
                .build();
    }
}