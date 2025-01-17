package com.example.cop_rut.service;
import com.example.cop_rut.dtos.base.ClientDto;
import com.example.cop_rut.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    void save(Client client);
    ClientDto add(ClientDto clientDto);
    ClientDto getClientByUuid(String clientId);
    List<ClientDto> getAllClients();
    ClientDto findClientsByPhoneNumber(String phoneNumber);
    ClientDto updateClient(ClientDto clientDto);
    void addOrderInClient(String clientId, String orderId);
    void deleteClient(String clientId);

}
