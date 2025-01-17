package com.example.cop_rut.controllers.rest_hateoas;

import com.example.cop_rut.controllers.helperClass.HelperHateoas;

import com.example.cop_rut.dtos.base.OrderDto;
import com.example.cop_rut.dtos.base.SpaceDto;

import com.example.cop_rut.model.enam.order.ExecutionStatus;
import com.example.cop_rut.service.OrderService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/order")
@Tag(name = "Заказ")
public class OrderController {
    private OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<HelperHateoas> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto orderDto1 = orderService.add(orderDto);

        HelperHateoas helperHateoas = new HelperHateoas(orderDto1);
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrderById(null)).withRel("getOrderById"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getAllOrders()).withRel("getAllOrders"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrdersByClientId(null)).withRel("getOrdersByClientId"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrdersByExecutionDate(orderDto1.getExecution())).withRel("getOrdersByExecutionDate"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).updateOrder(null)).withRel("updateOrder"));

        helperHateoas.addAction("update", "/api/order/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/order/get" + orderDto1.getUuid(), "GET");
        helperHateoas.addAction("all", "/api/order/all", "GET");
        helperHateoas.addAction("client", "/api/order/client" + orderDto1.getClient(), "GET");
        helperHateoas.addAction("create", "/api/order/add", "POST");
        helperHateoas.addAction("execution", "/api/order/execution" + orderDto1.getExecution(), "GET");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<HelperHateoas> getOrderById(@RequestParam String uuid) {
        OrderDto orderDto = orderService.getOrderByUuid(uuid);

        HelperHateoas helperHateoas = new HelperHateoas(orderDto);
        helperHateoas.add(linkTo(methodOn(OrderController.class).createOrder(null)).withRel("createOrder"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getAllOrders()).withRel("getAllOrders"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrdersByClientId(null)).withRel("getOrdersByClientId"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrdersByExecutionDate(orderDto.getExecution())).withRel("getOrdersByExecutionDate"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).updateOrder(null)).withRel("updateOrder"));

        helperHateoas.addAction("update", "/api/order/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/order/get" + orderDto.getUuid(), "GET");
        helperHateoas.addAction("all", "/api/order/all", "GET");
        helperHateoas.addAction("client", "/api/order/client" + orderDto.getClient(), "GET");
        helperHateoas.addAction("create", "/api/order/add", "POST");
        helperHateoas.addAction("execution", "/api/order/execution" + orderDto.getExecution(), "GET");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<HelperHateoas> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders();

        HelperHateoas helperHateoas = new HelperHateoas(orders);
        helperHateoas.add(linkTo(methodOn(OrderController.class).createOrder(null)).withRel("createOrder"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrderById("uuid")).withRel("getOrderById"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrdersByClientId("clientUuid")).withRel("getOrdersByClientId"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrdersByExecutionDate(LocalDateTime.now())).withRel("getOrdersByExecutionDate"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).updateOrder(null)).withRel("updateOrder"));

        helperHateoas.addAction("update", "/api/order/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/order/get/", "GET");
        helperHateoas.addAction("all", "/api/order/all", "GET");
        helperHateoas.addAction("client", "/api/order/client/", "GET");
        helperHateoas.addAction("create", "/api/order/add", "POST");
        helperHateoas.addAction("execution", "/api/order/execution/", "GET");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @GetMapping("/client")
    public ResponseEntity<HelperHateoas> getOrdersByClientId(@RequestParam String clientId) {
        List<OrderDto> orders = orderService.getOrdersByClientUuid(clientId);

        HelperHateoas helperHateoas = new HelperHateoas(orders);
        helperHateoas.add(linkTo(methodOn(OrderController.class).createOrder(null)).withRel("createOrder"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrderById(null)).withRel("getOrderById"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getAllOrders()).withRel("getAllOrders"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrdersByExecutionDate(LocalDateTime.now())).withRel("getOrdersByExecutionDate"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).updateOrder(null)).withRel("updateOrder"));

        helperHateoas.addAction("update", "/api/order/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/order/get/", "GET");
        helperHateoas.addAction("all", "/api/order/all", "GET");
        helperHateoas.addAction("client", "/api/order/client/", "GET");
        helperHateoas.addAction("create", "/api/order/add", "POST");
        helperHateoas.addAction("execution", "/api/order/execution/", "GET");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @GetMapping("/execution")
    public ResponseEntity<HelperHateoas> getOrdersByExecutionDate(@RequestParam LocalDateTime executionDate) {
        List<OrderDto> orders = orderService.getOrdersByExecutionDate(LocalDate.from(executionDate).atStartOfDay());

        HelperHateoas helperHateoas = new HelperHateoas(orders);
        helperHateoas.add(linkTo(methodOn(OrderController.class).createOrder(null)).withRel("createOrder"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrderById("uuid")).withRel("getOrderById"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getAllOrders()).withRel("getAllOrders"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrdersByClientId("clientUuid")).withRel("getOrdersByClientId"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).updateOrder(null)).withRel("updateOrder"));

        helperHateoas.addAction("update", "/api/order/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/order/get/", "GET");
        helperHateoas.addAction("all", "/api/order/all", "GET");
        helperHateoas.addAction("client", "/api/order/client/", "GET");
        helperHateoas.addAction("create", "/api/order/add", "POST");
        helperHateoas.addAction("execution", "/api/order/execution/", "GET");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HelperHateoas> updateOrder(@RequestBody OrderDto orderDto) {
        OrderDto orderDto1 = orderService.updateOrder(orderDto);

        HelperHateoas helperHateoas = new HelperHateoas(orderDto1);
        helperHateoas.add(linkTo(methodOn(OrderController.class).createOrder(null)).withRel("createOrder"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrderById(orderDto1.getUuid())).withRel("getOrderById"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getAllOrders()).withRel("getAllOrders"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrdersByClientId(orderDto1.getClient())).withRel("getOrdersByClientId"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrdersByExecutionDate(orderDto1.getExecution())).withRel("getOrdersByExecutionDate"));

        helperHateoas.addAction("update", "/api/order/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/order/get/" + orderDto1.getUuid(), "GET");
        helperHateoas.addAction("all", "/api/order/all", "GET");
        helperHateoas.addAction("client", "/api/order/client/" + orderDto1.getClient(), "GET");
        helperHateoas.addAction("create", "/api/order/add", "POST");
        helperHateoas.addAction("execution", "/api/order/execution/" + orderDto1.getExecution(), "GET");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @DeleteMapping("/archived/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable String uuid) {
        orderService.ArchivedOrder(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/unarchive/{orderId}")
    public ResponseEntity<Void> unArchiveOrder(@PathVariable String orderId) {
        orderService.UnArchivedOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/brigade")
    public ResponseEntity<HelperHateoas> getOrderByBrigadeUuid(@RequestParam String brigadeId) {
        List<OrderDto> orders = orderService.getOrderByBrigadeUuid(brigadeId);

        HelperHateoas helperHateoas = new HelperHateoas(orders);
        helperHateoas.add(linkTo(methodOn(OrderController.class).createOrder(null)).withRel("createOrder"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getAllOrders()).withRel("getAllOrders"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrdersByClientId(null)).withRel("getOrdersByClientId"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrdersByExecutionDate(LocalDateTime.now())).withRel("getOrdersByExecutionDate"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).updateOrder(null)).withRel("updateOrder"));

        helperHateoas.addAction("update", "/api/order/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/order/get/", "GET");
        helperHateoas.addAction("all", "/api/order/all", "GET");
        helperHateoas.addAction("client", "/api/order/client/", "GET");
        helperHateoas.addAction("create", "/api/order/add", "POST");
        helperHateoas.addAction("execution", "/api/order/execution/", "GET");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @PutMapping("/status")
    public ResponseEntity<HelperHateoas> updateOrderExecutionStatus(@RequestParam String orderId, @RequestParam ExecutionStatus executionStatus) {
        orderService.updateOrderExecutionStatus(orderId, executionStatus);

        OrderDto updatedOrder = orderService.getOrderByUuid(orderId); // Получаем обновленный заказ
        HelperHateoas helperHateoas = new HelperHateoas(updatedOrder);
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrderById(orderId)).withRel("getOrderById"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getAllOrders()).withRel("getAllOrders"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrdersByClientId(updatedOrder.getClient())).withRel("getOrdersByClientId"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrdersByExecutionDate(updatedOrder.getExecution())).withRel("getOrdersByExecutionDate"));

        helperHateoas.addAction("update", "/api/order/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/order/get/" + updatedOrder.getUuid(), "GET");
        helperHateoas.addAction("all", "/api/order/all", "GET");
        helperHateoas.addAction("client", "/api/order/client/" + updatedOrder.getClient(), "GET");
        helperHateoas.addAction("create", "/api/order/add", "POST");
        helperHateoas.addAction("execution", "/api/order/execution/" + updatedOrder.getExecution(), "GET");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }

    @PostMapping("/order/{orderId}/space")
    public ResponseEntity<HelperHateoas> addSpaceInOrderSpaceList(@PathVariable String orderId, @RequestBody SpaceDto spaceDto) {
        orderService.addSpaceInOrderSpaceList(orderId, spaceDto);

        OrderDto orderDto = orderService.getOrderByUuid(orderId);

        HelperHateoas helperHateoas = new HelperHateoas(orderDto);

        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrderById(orderId)).withRel("getOrderById"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getAllOrders()).withRel("getAllOrders"));
        helperHateoas.add(linkTo(methodOn(OrderController.class).getOrdersByClientId(orderDto.getClient())).withRel("getOrdersByClientId"));
//        helperHateoas.add(linkTo(methodOn(OrderController.class).updateOrder()).withRel("updateOrder"));

        helperHateoas.addAction("update", "/api/order/update", "PUT", "application/json");
        helperHateoas.addAction("get", "/api/order/get/" + orderDto.getUuid(), "GET");
        helperHateoas.addAction("all", "/api/order/all", "GET");
        helperHateoas.addAction("client", "/api/order/client/" + orderDto.getClient(), "GET");
        helperHateoas.addAction("create", "/api/order/add", "POST");
        helperHateoas.addAction("execution", "/api/order/execution/" + orderDto.getExecution(), "GET");

        return new ResponseEntity<>(helperHateoas, HttpStatus.OK);
    }
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
