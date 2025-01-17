package com.example.cop_rut.controllers.testData;

import com.example.cop_rut.model.enam.room.*;
import com.example.cop_rut.model.enam.order.*;
import com.example.cop_rut.model.*;


import com.example.cop_rut.service.BrigadeService;
import com.example.cop_rut.service.ClientService;
import com.example.cop_rut.service.OrderService;
import com.example.cop_rut.service.WorkerService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/testdata")
@Tag(name = "Генерация тестовых данных")
public class GenerateTestData {
    @Autowired
    private ModelMapper modelMapper;
    private BrigadeService brigadeService;
    private ClientService clientService;
    private OrderService orderService;
    private WorkerService workerService;

    @Autowired
    private MongoTemplate mongoTemplate;


    @PostMapping("/generate")
    public ResponseEntity<String> generateTestData() {

        mongoTemplate.getDb().drop();

        Random random = new Random();

        for (int i = 0; i < 3; i++){

//            Brigade brigade = new Brigade();
//            brigade.setUuid(UUID.randomUUID().toString());
//            brigade.setPhoneNumber("7910456789" + random.nextInt(1));
//            brigade.setFreeness(new Random().nextBoolean());
//            brigade.setWorkersId(new ArrayList<>());
//            brigade.setUpdateDate(LocalDateTime.now());
//            brigade.setCreateDate(LocalDateTime.now());

            Client client = new Client();
            client.setUuid(UUID.randomUUID().toString());
            client.setName("Client" + random.nextInt(3));
            client.setPhoneNumber("7960456786" + random.nextInt(1));
            client.setEmail("client" + random.nextInt() + "@example.com");
            client.setUpdateDate(LocalDateTime.now());
            client.setCreateDate(LocalDateTime.now());

            List<String> workersList = new ArrayList<>();
            Worker worker = new Worker();
            worker.setUuid(UUID.randomUUID().toString());
            worker.setName("Worker" + random.nextInt(3));
            worker.setPhoneNumber("7960456786" + random.nextInt(1));
            worker.setOnShift(new Random().nextBoolean());
//            worker.setBrigade(brigade.getUuid());
            worker.setUpdateDate(LocalDateTime.now());
            worker.setCreateDate(LocalDateTime.now());
            workerService.save(worker);
            workersList.add(worker.getUuid());
//            brigade.setWorkersId(workersList);

            Order order = new Order();
            order.setUuid(UUID.randomUUID().toString());
            order.setCleaningType(CleaningType.STANDARD);
            order.setExecutionStatus(ExecutionStatus.PROCESSING);
            order.setExecutionDate(LocalDateTime.now().plusDays(1));
            order.setOrderNumber("OrderNumber" + random.nextInt());
            order.setDistanceFromCenter(DistanceFromCenter.NEAR);
            order.setAdditionalServices(Collections.singletonList(AdditionalService.CARPET_CLEANING));
            order.setCustomerComment("Customer comment for order " + random.nextInt());
            order.setExecutorComment("Executor comment for order " + random.nextInt());
            order.setRedFlag(null);
            order.setCost(BigDecimal.valueOf(100 + 1 * 10));
            order.setArchived(false);

            order.setSpace(generateRooms());

            order.setClient(client.getUuid());
//            order.setBrigade(brigade.getUuid());
            order.setUpdateDate(LocalDateTime.now());
            order.setCreateDate(LocalDateTime.now());

            List<String> orders = new ArrayList<>();
            orders.add(order.getUuid());

            client.setOrders(orders);
//            brigade.setOrder(order.getUuid());
            clientService.save(client);
//            brigadeService.save(brigade);

            orderService.save(order);
        }

        return new ResponseEntity<>("Данные добавлены", HttpStatus.OK);
    }

    private List<Space> generateRooms() {
        List<Space> roomDtos = new ArrayList<>();

        int numberOfRooms = new Random().nextInt(3) + 1;
        for (int j = 0; j < numberOfRooms; j++) {
                Space space = new Space(SpaceType.WAREHOUSE, 1, new Random().nextDouble() * 100);
                roomDtos.add(space);
        }
        return roomDtos;
    }

    @Autowired
    public void setBrigadeService(BrigadeService brigadeService) {
        this.brigadeService = brigadeService;
    }
    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    @Autowired
    public void setWorkerService(WorkerService workerService) {
        this.workerService = workerService;
    }
}
