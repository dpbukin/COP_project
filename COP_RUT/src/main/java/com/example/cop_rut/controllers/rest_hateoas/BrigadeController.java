package com.example.cop_rut.controllers.rest_hateoas;

import com.example.cop_rut.controllers.helperClass.HelperHateoas;
import com.example.cop_rut.service.BrigadeService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.cop_rut.dtos.base.BrigadeDto;

@RestController
@RequestMapping("/api/brigade")
@Tag(name = "Бригада")
public class BrigadeController {
    private BrigadeService brigadeService;

    @PostMapping("/add")
    public ResponseEntity<HelperHateoas> createBrigade(@RequestBody BrigadeDto brigadeDto) {
        BrigadeDto brigadeDto1 = brigadeService.add(brigadeDto);

        HelperHateoas helperHateoas = new HelperHateoas(brigadeDto1);
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).getBrigadeById(brigadeDto1.getUuid())).withRel("getBrigadeById"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).getAllBrigades()).withRel("getAllBrigades"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).freeBrigade("uuid")).withRel("freeBrigade"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).updateBrigade(null)).withRel("updateBrigade"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).removeBrigadeByUuid(brigadeDto1.getUuid())).withRel("removeBrigadeByUuid"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).assignBrigadeToOrder("orderId")).withRel("assignBrigadeToOrder"));

        helperHateoas.addAction("update", "/api/brigade/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/brigade/get/{uuid}", "GET");
        helperHateoas.addAction("all", "/api/brigade/all", "GET");
        helperHateoas.addAction("free", "/api/brigade/free", "GET");
        helperHateoas.addAction("create", "/api/brigades/add", "POST");
        helperHateoas.addAction("delete", "/api/brigade/remove/{uuid}", "DELETE");
        helperHateoas.addAction("assign", "/api/brigade/assign", "POST");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @GetMapping("/get/{uuid}")
    public ResponseEntity<HelperHateoas> getBrigadeById(@PathVariable String uuid) {
        BrigadeDto brigadeDto = brigadeService.getBrigadeByUuid(uuid);

        HelperHateoas helperHateoas = new HelperHateoas(brigadeDto);
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).createBrigade(null)).withRel("createBrigade"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).getAllBrigades()).withRel("getAllBrigades"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).freeBrigade(uuid)).withRel("freeBrigade"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).updateBrigade(brigadeDto)).withRel("updateBrigade"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).removeBrigadeByUuid(uuid)).withRel("removeBrigadeByUuid"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).assignBrigadeToOrder("orderId")).withRel("assignBrigadeToOrder"));

        helperHateoas.addAction("update", "/api/brigade/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/brigade/get/{uuid}", "GET");
        helperHateoas.addAction("all", "/api/brigade/all", "GET");
        helperHateoas.addAction("free", "/api/brigade/free", "GET");
        helperHateoas.addAction("create", "/api/brigades/add", "POST");
        helperHateoas.addAction("delete", "/api/brigade/remove/{uuid}", "DELETE");
        helperHateoas.addAction("assign", "/api/brigade/assign", "POST");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<HelperHateoas> getAllBrigades() {
        List<BrigadeDto> brigades = brigadeService.getAllBrigades();

        HelperHateoas helperHateoas = new HelperHateoas(brigades);
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).getBrigadeById("uuid")).withRel("getBrigadeById"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).createBrigade(null)).withRel("createBrigade"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).freeBrigade("uuid")).withRel("freeBrigade"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).updateBrigade(null)).withRel("updateBrigade"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).assignBrigadeToOrder("orderId")).withRel("assignBrigadeToOrder"));

        helperHateoas.addAction("update", "/api/brigade/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/brigade/get/{uuid}", "GET");
        helperHateoas.addAction("all", "/api/brigade/all", "GET");
        helperHateoas.addAction("free", "/api/brigade/free", "GET");
        helperHateoas.addAction("create", "/api/brigades/add", "POST");
        helperHateoas.addAction("delete", "/api/brigade/remove/{uuid}", "DELETE");
        helperHateoas.addAction("assign", "/api/brigade/assign", "POST");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @GetMapping("/free")
    public ResponseEntity<HelperHateoas> freeBrigade(String uuid) {
        String freeBrigades = brigadeService.freeBrigade(uuid);

        HelperHateoas helperHateoas = new HelperHateoas(freeBrigades);
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).getBrigadeById("uuid")).withRel("getBrigadeById"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).createBrigade(null)).withRel("createBrigade"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).getAllBrigades()).withRel("getAllBrigades"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).updateBrigade(null)).withRel("updateBrigade"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).assignBrigadeToOrder("orderId")).withRel("assignBrigadeToOrder"));

        helperHateoas.addAction("update", "/api/brigade/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/brigade/get/{uuid}", "GET");
        helperHateoas.addAction("all", "/api/brigade/all", "GET");
        helperHateoas.addAction("free", "/api/brigade/free", "GET");
        helperHateoas.addAction("create", "/api/brigades/add", "POST");
        helperHateoas.addAction("delete", "/api/brigade/remove/{uuid}", "DELETE");
        helperHateoas.addAction("assign", "/api/brigade/assign", "POST");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<HelperHateoas> updateBrigade(@RequestBody BrigadeDto brigadeDto) {
        BrigadeDto brigadeDto1 = brigadeService.updateBrigade(brigadeDto);

        HelperHateoas helperHateoas = new HelperHateoas(brigadeDto1);
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).getBrigadeById(brigadeDto1.getUuid())).withRel("getBrigadeById"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).createBrigade(null)).withRel("createBrigade"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).getAllBrigades()).withRel("getAllBrigades"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).freeBrigade("uuid")).withRel("freeBrigade"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).assignBrigadeToOrder("orderId")).withRel("assignBrigadeToOrder"));

        helperHateoas.addAction("update", "/api/brigade/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/brigade/get/{uuid}", "GET");
        helperHateoas.addAction("all", "/api/brigade/all", "GET");
        helperHateoas.addAction("free", "/api/brigade/free", "GET");
        helperHateoas.addAction("create", "/api/brigades/add", "POST");
        helperHateoas.addAction("delete", "/api/brigade/remove/{uuid}", "DELETE");
        helperHateoas.addAction("assign", "/api/brigade/assign", "POST");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{uuid}")
    public ResponseEntity<HelperHateoas> removeBrigadeByUuid(@PathVariable String uuid) {
        brigadeService.removeBrigadeByUuid(uuid);

        HelperHateoas helperHateoas = new HelperHateoas("Brigade removed successfully");
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).getAllBrigades()).withRel("getAllBrigades"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).createBrigade(null)).withRel("createBrigade"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).freeBrigade(uuid)).withRel("freeBrigade"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).assignBrigadeToOrder("orderId")).withRel("assignBrigadeToOrder"));

        helperHateoas.addAction("create", "/api/brigades/add", "POST");
        helperHateoas.addAction("all", "/api/brigade/all", "GET");
        helperHateoas.addAction("assign", "/api/brigade/assign", "POST");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @PostMapping("/assign")
    public ResponseEntity<HelperHateoas> assignBrigadeToOrder(@RequestParam String orderId) {
        String brigadeUuid = brigadeService.assignBrigadeToOrder(orderId);

        HelperHateoas helperHateoas = new HelperHateoas(brigadeUuid);
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).getBrigadeById(brigadeUuid)).withRel("getBrigadeById"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).freeBrigade("uuid")).withRel("freeBrigade"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).getAllBrigades()).withRel("getAllBrigades"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).updateBrigade(null)).withRel("updateBrigade"));
        helperHateoas.add(linkTo(methodOn(BrigadeController.class).createBrigade(null)).withRel("createBrigade"));

        helperHateoas.addAction("update", "/api/brigade/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/brigade/get/{uuid}", "GET");
        helperHateoas.addAction("all", "/api/brigade/all", "GET");
        helperHateoas.addAction("free", "/api/brigade/free", "GET");
        helperHateoas.addAction("create", "/api/brigades/add", "POST");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @Autowired
    public void setBrigadeService(BrigadeService brigadeService) {
        this.brigadeService = brigadeService;
    }
}

