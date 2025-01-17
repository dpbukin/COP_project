package com.example.cop_rut.controllers.dataFetchers;

import com.example.cop_rut.dtos.base.ClientDto;
import com.example.cop_rut.service.ClientService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@DgsComponent
public class ClientDataFetcher {

    private ClientService clientService;
    @DgsQuery
    public List<ClientDto> clients() {
        return clientService.getAllClients();
    }

    @DgsQuery
    public ClientDto clientById(@InputArgument String uuid) {
        return clientService.getClientByUuid(uuid);
    }

    @DgsQuery
    public ClientDto clientsByPhoneNumber(@InputArgument String phone){
        return clientService.findClientsByPhoneNumber(phone);
    }
    @DgsMutation
    public ClientDto createClient(@InputArgument String name, @InputArgument String phone, @InputArgument String email, @InputArgument List<String> orders){
        ClientDto clientDto = new ClientDto();
        clientDto.setName(name);
        clientDto.setPhoneNumber(phone);
        clientDto.setEmail(email);
        clientDto.setOrders(orders);

        return clientService.add(clientDto);
    }
    @DgsMutation
    public ClientDto updateClient(@InputArgument String uuid, @InputArgument String name, @InputArgument String phone, @InputArgument String email, @InputArgument List<String> orders){
        ClientDto clientDto = new ClientDto();
        clientDto.setUuid(uuid);
        clientDto.setName(name);
        clientDto.setPhoneNumber(phone);
        clientDto.setEmail(email);
        clientDto.setOrders(orders);

        return clientService.updateClient(clientDto);
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}


