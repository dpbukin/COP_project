package com.example.cop_rut.controllers.rest_hateoas;

import com.example.cop_rut.controllers.helperClass.HelperHateoas;
import com.example.cop_rut.service.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import com.example.cop_rut.dtos.base.ClientDto;

@RestController
@RequestMapping("/api/client")
@Tag(name = "Клиент")
public class ClientController {
    private ClientService clientService;

    @PostMapping("/add")
    public ResponseEntity<HelperHateoas> createClient(@RequestBody ClientDto clientDto) {
        ClientDto clientDto1 = clientService.add(clientDto);

        HelperHateoas helperHateoas = new HelperHateoas(clientDto);
        helperHateoas.add(linkTo(methodOn(ClientController.class).updateClient(clientDto)).withRel("update"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).deleteClientById(clientDto1.getUuid())).withRel("deleteById"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).getClientById(clientDto1.getUuid())).withRel("getById"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).findClientsByPhoneNumber(clientDto1.getPhoneNumber())).withRel("findByPhoneNumber"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).getAllClients()).withRel("getAllClients"));

        helperHateoas.addAction("update", "/api/client/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/client/get/" + clientDto.getUuid(), "GET");
        helperHateoas.addAction("all", "/api/client/all", "GET");
        helperHateoas.addAction("getByNumber", "/api/client/phone/" + clientDto.getPhoneNumber(), "GET");
        helperHateoas.addAction("create", "/api/client/add", "POST");
        helperHateoas.addAction("delete", "/api/client/del/" + clientDto.getUuid(), "DELETE");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @GetMapping("/get/{uuid}")
    public ResponseEntity<HelperHateoas> getClientById(@PathVariable("uuid") String uuid) {
        ClientDto clientDto = clientService.getClientByUuid(uuid);

        if (clientDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HelperHateoas helperHateoas = new HelperHateoas(clientDto);
        helperHateoas.add(linkTo(methodOn(ClientController.class).updateClient(clientDto)).withRel("update"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).deleteClientById(clientDto.getUuid())).withRel("deleteById"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).createClient(null)).withRel("create"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).findClientsByPhoneNumber(clientDto.getPhoneNumber())).withRel("findByPhoneNumber"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).getAllClients()).withRel("getAllClients"));

        helperHateoas.addAction("update", "/api/client/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/client/get/" + clientDto.getUuid(), "GET");
        helperHateoas.addAction("all", "/api/client/all", "GET");
        helperHateoas.addAction("getByNumber", "/api/client/phone/" + clientDto.getPhoneNumber(), "GET");
        helperHateoas.addAction("create", "/api/client/add", "POST");
        helperHateoas.addAction("delete", "/api/client/del/" + clientDto.getUuid(), "DELETE");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<HelperHateoas> getAllClients() {
        List<ClientDto> clients = clientService.getAllClients();

        HelperHateoas helperHateoas = new HelperHateoas(clients);
        helperHateoas.add(linkTo(methodOn(ClientController.class).updateClient(null)).withRel("update"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).getClientById("uuid")).withRel("getById"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).deleteClientById("uuid")).withRel("deleteById"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).createClient(null)).withRel("create"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).findClientsByPhoneNumber("phoneNumber")).withRel("findByPhoneNumber"));

        helperHateoas.addAction("update", "/api/client/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/client/get/", "GET");
        helperHateoas.addAction("all", "/api/client/all", "GET");
        helperHateoas.addAction("getByNumber", "/api/client/phone/", "GET");
        helperHateoas.addAction("create", "/api/client/add", "POST");
        helperHateoas.addAction("delete", "/api/client/del/", "DELETE");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<HelperHateoas> findClientsByPhoneNumber(@PathVariable String phoneNumber) {
        ClientDto clientDto = clientService.findClientsByPhoneNumber(phoneNumber);

        if (clientDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HelperHateoas helperHateoas = new HelperHateoas(clientDto);
        helperHateoas.add(linkTo(methodOn(ClientController.class).updateClient(clientDto)).withRel("update"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).getClientById(clientDto.getUuid())).withRel("getById"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).deleteClientById(clientDto.getUuid())).withRel("deleteById"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).createClient(clientDto)).withRel("create"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).getAllClients()).withRel("getAllClients"));

        helperHateoas.addAction("update", "/api/client/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/client/get/" + clientDto.getUuid(), "GET");
        helperHateoas.addAction("all", "/api/client/all", "GET");
        helperHateoas.addAction("getByNumber", "/api/client/phone/" + clientDto.getPhoneNumber(), "GET");
        helperHateoas.addAction("create", "/api/client/add", "POST");
        helperHateoas.addAction("delete", "/api/client/del/" + clientDto.getUuid(), "DELETE");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<HelperHateoas> updateClient(@RequestBody ClientDto clientDto) {
        clientService.updateClient(clientDto);

        ClientDto clientDtoResp = clientService.getClientByUuid(clientDto.getUuid());

        HelperHateoas helperHateoas = new HelperHateoas(clientDtoResp);
        helperHateoas.add(linkTo(methodOn(ClientController.class).getClientById(clientDtoResp.getUuid())).withRel("getById"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).deleteClientById(clientDtoResp.getUuid())).withRel("deleteById"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).createClient(null)).withRel("create"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).getAllClients()).withRel("getAllClients"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).findClientsByPhoneNumber(clientDtoResp.getPhoneNumber())).withRel("findByPhoneNumber"));

        helperHateoas.addAction("update", "/api/client/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/client/get/" + clientDto.getUuid(), "GET");
        helperHateoas.addAction("all", "/api/client/all", "GET");
        helperHateoas.addAction("getByNumber", "/api/client/phone/" + clientDto.getPhoneNumber(), "GET");
        helperHateoas.addAction("create", "/api/client/add", "POST");
        helperHateoas.addAction("delete", "/api/client/del/" + clientDto.getUuid(), "DELETE");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @PostMapping("/client/{clientId}/order/{orderId}")
    public ResponseEntity<HelperHateoas> addOrderInClient(@PathVariable String clientId, @PathVariable String orderId) {
        clientService.addOrderInClient(clientId, orderId);

        ClientDto clientDto = clientService.getClientByUuid(clientId);

        HelperHateoas helperHateoas = new HelperHateoas(clientDto);

        helperHateoas.add(linkTo(methodOn(ClientController.class).getClientById(clientId)).withRel("getById"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).getAllClients()).withRel("getAllClients"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).findClientsByPhoneNumber(clientDto.getPhoneNumber())).withRel("findByPhoneNumber"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).updateClient(clientDto)).withRel("update"));
        helperHateoas.add(linkTo(methodOn(ClientController.class).deleteClientById(clientDto.getUuid())).withRel("deleteById"));

        helperHateoas.addAction("update", "/api/client/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/client/get/" + clientDto.getUuid(), "GET");
        helperHateoas.addAction("all", "/api/client/all", "GET");
        helperHateoas.addAction("getByNumber", "/api/client/phone/" + clientDto.getPhoneNumber(), "GET");
        helperHateoas.addAction("create", "/api/client/add", "POST");
        helperHateoas.addAction("delete", "/api/client/del/" + clientDto.getUuid(), "DELETE");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @DeleteMapping("/del/{uuid}")
    public ResponseEntity<HelperHateoas> deleteClientById(@PathVariable String uuid) {
        clientService.deleteClient(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}

