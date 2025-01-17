package com.example.cop_rut_contracts.service;


import com.example.cop_rut_contracts.dtos.base.ClientDto;

import java.util.List;

public interface ClientService {
//    void save(Client client);
    ClientDto add(ClientDto clientDto);
    ClientDto getClientByUuid(String clientId);
    List<ClientDto> getAllClients();
    ClientDto findClientsByPhoneNumber(String phoneNumber);
    ClientDto updateClient(ClientDto clientDto);
    void addOrderInClient(String clientId, String orderId);
    void deleteClient(String clientId);

}
