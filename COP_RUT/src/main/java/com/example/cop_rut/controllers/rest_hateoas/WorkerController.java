package com.example.cop_rut.controllers.rest_hateoas;

import com.example.cop_rut.controllers.helperClass.HelperHateoas;
import com.example.cop_rut.service.WorkerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.cop_rut.dtos.base.WorkerDto;

@RestController
@RequestMapping("/api/worker")
@Tag(name = "Работник")
public class WorkerController {
    private WorkerService workerService;

    @PostMapping("/add")
    public ResponseEntity<HelperHateoas> createWorker(@RequestBody WorkerDto workerDto) {
        WorkerDto workerDto1 = workerService.add(workerDto);

        HelperHateoas helperHateoas = new HelperHateoas(workerDto1);
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getWorkerById(workerDto1.getUuid())).withRel("getWorkerById"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getAllWorkers()).withRel("getAllWorkers"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getWorkersByBrigade(workerDto1.getBrigade())).withRel("getWorkersByBrigade"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getWorkersOnShift()).withRel("getWorkersOnShift"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).updateWorker(null)).withRel("updateWorker"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).deleteWorkerById(workerDto1.getUuid())).withRel("deleteWorkerById"));

        helperHateoas.addAction("update", "/api/worker/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/worker/get/" + workerDto1.getUuid(), "GET");
        helperHateoas.addAction("all", "/api/worker/all", "GET");
        helperHateoas.addAction("brigade", "/api/worker/brigade/" + workerDto1.getBrigade(), "GET");
        helperHateoas.addAction("create", "/api/worker/add", "POST");
        helperHateoas.addAction("shift", "/api/worker/shift", "GET");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @GetMapping("/get/{uuid}")
    public ResponseEntity<HelperHateoas> getWorkerById(@PathVariable String uuid) {
        WorkerDto workerDto1 = workerService.getWorkerByUuid(uuid);

        HelperHateoas helperHateoas = new HelperHateoas(workerDto1);
        helperHateoas.add(linkTo(methodOn(WorkerController.class).createWorker(null)).withRel("createWorker"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getAllWorkers()).withRel("getAllWorkers"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getWorkersByBrigade(workerDto1.getBrigade())).withRel("getWorkersByBrigade"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getWorkersOnShift()).withRel("getWorkersOnShift"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).updateWorker(null)).withRel("updateWorker"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).deleteWorkerById(workerDto1.getUuid())).withRel("deleteWorkerById"));

        helperHateoas.addAction("update", "/api/worker/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/worker/get/" + workerDto1.getUuid(), "GET");
        helperHateoas.addAction("all", "/api/worker/all", "GET");
        helperHateoas.addAction("brigade", "/api/worker/brigade/" + workerDto1.getBrigade(), "GET");
        helperHateoas.addAction("create", "/api/worker/add", "POST");
        helperHateoas.addAction("shift", "/api/worker/shift", "GET");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<HelperHateoas> getAllWorkers() {
        List<WorkerDto> workers = workerService.getAllWorkers();

        HelperHateoas helperHateoas = new HelperHateoas(workers);
        helperHateoas.add(linkTo(methodOn(WorkerController.class).createWorker(null)).withRel("createWorker"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getWorkerById("uuid")).withRel("getWorkerById"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getWorkersByBrigade("brigadeUuid")).withRel("getWorkersByBrigade"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getWorkersOnShift()).withRel("getWorkersOnShift"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).updateWorker(null)).withRel("updateWorker"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).deleteWorkerById("uuid")).withRel("deleteWorkerById"));

        helperHateoas.addAction("update", "/api/worker/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/worker/get/", "GET");
        helperHateoas.addAction("all", "/api/worker/all", "GET");
        helperHateoas.addAction("brigade", "/api/worker/brigade/", "GET");
        helperHateoas.addAction("create", "/api/worker/add", "POST");
        helperHateoas.addAction("shift", "/api/worker/shift", "GET");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @GetMapping("/brigade/{brigadeId}")
    public ResponseEntity<HelperHateoas> getWorkersByBrigade(@PathVariable String brigadeId) {
        List<WorkerDto> workers = workerService.getWorkersByBrigade(brigadeId);

        HelperHateoas helperHateoas = new HelperHateoas(workers);
        helperHateoas.add(linkTo(methodOn(WorkerController.class).createWorker(null)).withRel("createWorker"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getWorkerById("uuid")).withRel("getWorkerById"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getAllWorkers()).withRel("getAllWorkers"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getWorkersOnShift()).withRel("getWorkersOnShift"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).updateWorker(null)).withRel("updateWorker"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).deleteWorkerById("uuid")).withRel("deleteWorkerById"));

        helperHateoas.addAction("update", "/api/worker/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/worker/get/", "GET");
        helperHateoas.addAction("all", "/api/worker/all", "GET");
        helperHateoas.addAction("brigade", "/api/worker/brigade/", "GET");
        helperHateoas.addAction("create", "/api/worker/add", "POST");
        helperHateoas.addAction("shift", "/api/worker/shift", "GET");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @GetMapping("/shift")
    public ResponseEntity<HelperHateoas> getWorkersOnShift() {
        List<WorkerDto> workers = workerService.getWorkersOnShift();

        HelperHateoas helperHateoas = new HelperHateoas(workers);
        helperHateoas.add(linkTo(methodOn(WorkerController.class).createWorker(null)).withRel("createWorker"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getWorkerById("uuid")).withRel("getWorkerById"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getAllWorkers()).withRel("getAllWorkers"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getWorkersByBrigade("brigadeUuid")).withRel("getWorkersByBrigade"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).updateWorker(null)).withRel("updateWorker"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).deleteWorkerById("uuid")).withRel("deleteWorkerById"));

        helperHateoas.addAction("update", "/api/worker/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/worker/get/", "GET");
        helperHateoas.addAction("all", "/api/worker/all", "GET");
        helperHateoas.addAction("brigade", "/api/worker/brigade/", "GET");
        helperHateoas.addAction("create", "/api/worker/add", "POST");
        helperHateoas.addAction("shift", "/api/worker/shift", "GET");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<HelperHateoas> updateWorker(@RequestBody WorkerDto workerDto) {
        WorkerDto workerDto1 = workerService.updateWorker(workerDto);

        HelperHateoas helperHateoas = new HelperHateoas(workerDto1);
        helperHateoas.add(linkTo(methodOn(WorkerController.class).createWorker(null)).withRel("createWorker"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getWorkerById(workerDto1.getUuid())).withRel("getWorkerById"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getAllWorkers()).withRel("getAllWorkers"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getWorkersByBrigade(workerDto1.getBrigade())).withRel("getWorkersByBrigade"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getWorkersOnShift()).withRel("getWorkersOnShift"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).deleteWorkerById(workerDto1.getUuid())).withRel("deleteWorkerById"));

        helperHateoas.addAction("update", "/api/worker/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/worker/get/" + workerDto1.getUuid(), "GET");
        helperHateoas.addAction("all", "/api/worker/all", "GET");
        helperHateoas.addAction("brigade", "/api/worker/brigade/" + workerDto1.getBrigade(), "GET");
        helperHateoas.addAction("create", "/api/worker/add", "POST");
        helperHateoas.addAction("shift", "/api/worker/shift", "GET");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @DeleteMapping("/dismissed/{uuid}")
    public ResponseEntity<Void> deleteWorkerById(@PathVariable String uuid) {
        workerService.dismissedWorkerByUuid(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/status/{workerId}")
    public ResponseEntity<HelperHateoas> updateWorkerStatus(@PathVariable String workerId, @RequestBody Boolean status) {
        workerService.updateWorkerStatus(workerId, status);

        WorkerDto workerDto = workerService.getWorkerByUuid(workerId);

        HelperHateoas helperHateoas = new HelperHateoas(workerDto);
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getWorkerById(workerId)).withRel("getWorkerById"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getAllWorkers()).withRel("getAllWorkers"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getWorkersByBrigade(workerDto.getBrigade())).withRel("getWorkersByBrigade"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).getWorkersOnShift()).withRel("getWorkersOnShift"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).updateWorker(null)).withRel("updateWorker"));
        helperHateoas.add(linkTo(methodOn(WorkerController.class).deleteWorkerById(workerId)).withRel("deleteWorkerById"));

        helperHateoas.addAction("update", "/api/worker/update/status/" + workerId, "PUT", "application/json");
        helperHateoas.addAction("get", "/api/worker/get/" + workerId, "GET");
        helperHateoas.addAction("all", "/api/worker/all", "GET");
        helperHateoas.addAction("brigade", "/api/worker/brigade/" + workerDto.getBrigade(), "GET");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @Autowired
    public void setWorkerService(WorkerService workerService) {
        this.workerService = workerService;
    }

}
