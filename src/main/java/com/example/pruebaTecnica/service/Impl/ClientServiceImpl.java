package com.example.pruebaTecnica.service.Impl;

import com.example.pruebaTecnica.dto.ClientDto;
import com.example.pruebaTecnica.dto.ClientDtoResponse;
import com.example.pruebaTecnica.entity.ClientEntity;
import com.example.pruebaTecnica.exception.ClientNotFoundException;
import com.example.pruebaTecnica.mapper.ClientMapper;
import com.example.pruebaTecnica.repository.ClientRepository;
import com.example.pruebaTecnica.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Validated
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clienRepository;

    private final ClientMapper clientMapper;

    @Override
    public List<ClientEntity> findAll() {
        return clienRepository.findAll();
    }

    @Override
    public ClientDtoResponse findById(Long id) throws ClientNotFoundException {
            Optional<ClientEntity> cliente = clienRepository.findById(id);

            if (!cliente.isPresent()){
                throw new ClientNotFoundException("Client is not available");
            }

            return ClientMapper.clienteEntityToClienClienteDtoResponse("Client is available: ", cliente.get());
        }

    @Override
    public Object save(ClientDto clientDataDto) {
        if (esMayorEdad(clientDataDto.getFechaNacimiento())){
            ClientEntity MapperClient = clientMapper.clienteDtoToEntity(clientDataDto);
            MapperClient.setFechaCreacion(LocalDateTime.now());
            MapperClient.setFechaModificacion(null);
            return clienRepository.save(MapperClient);
        }

        return "No es mayor de edad";
    }

    private boolean esMayorEdad (LocalDate fechaNacimiento){
        if (fechaNacimiento == null){
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
        }
        LocalDate hoy = LocalDate.now();
        Period edad = Period.between(fechaNacimiento, hoy);
        return edad.getYears() >= 18;
    }

    @Override
    public String delete(Long id) {
        try {
            clienRepository.deleteById(id);
            return ("Successfully deleted");
        } catch (Exception e) {
            return "Error";
        }
    }

    @Override
    public ClientDto update(Long id, ClientDto clienteDetails) throws ClientNotFoundException {
        ClientEntity existingCliente = clienRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Cliente no encontrado con id: "+id));

        existingCliente.setTipoIdentificacion(clienteDetails.getTipoIdentificacion());
        existingCliente.setNumeroIdentificacion(clienteDetails.getNumeroIdentificacion());
        existingCliente.setNombre(clienteDetails.getNombre());
        existingCliente.setApellido(clienteDetails.getApellido());
        existingCliente.setEmail(clienteDetails.getEmail());
        existingCliente.setFechaNacimiento(clienteDetails.getFechaNacimiento());
        existingCliente.setFechaModificacion(LocalDateTime.now());

        existingCliente = clienRepository.save(existingCliente);


        return ClientMapper.toClientDto(existingCliente);

    }

    @Override
    public ClientDtoResponse findClienteByName(String nombre) throws ClientNotFoundException {
            Optional<ClientEntity> client = clienRepository.findByNombre(nombre);
            if (!client.isPresent()){
                throw new IllegalArgumentException("El cliente no ha sido encontrado");
            }
            return clientMapper.clienteEntityToClienClienteDtoResponse("El cliente ha sido encontrado: " ,client.get());
        }
    }




