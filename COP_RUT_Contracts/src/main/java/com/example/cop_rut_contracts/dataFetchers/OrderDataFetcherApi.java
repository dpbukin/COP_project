package com.example.cop_rut_contracts.dataFetchers;

import com.example.cop_rut_contracts.dtos.base.OrderDto;
import com.example.cop_rut_contracts.dtos.base.SpaceDto;
import com.example.cop_rut_contracts.enam.order.*;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@DgsComponent
@Schema(description = "GraphQL API для работы с заказами")
public interface OrderDataFetcherApi {

    @DgsQuery
    @Schema(description = "Получить информацию о заказе по ID")
    OrderDto getOrderById(@InputArgument String uuid);

    @DgsQuery
    @Schema(description = "Получить список всех заказов")
    List<OrderDto> getAllOrders();

    @DgsQuery
    @Schema(description = "Получить заказы по ID клиента")
    List<OrderDto> getOrdersByClientId(@InputArgument String uuid);

    @DgsQuery
    @Schema(description = "Получить заказы по дате исполнения")
    List<OrderDto> getOrdersByExecutionDate(@InputArgument String executionDate);

    @DgsMutation
    @Schema(description = "Создать новый заказ")
    OrderDto createOrder(
            @InputArgument CleaningType cleaningType,
            @InputArgument ExecutionStatus executionStatus,
            @InputArgument String execution,
            @InputArgument String createDate,
            @InputArgument String orderNumber,
            @InputArgument DistanceFromCenter distanceFromCenter,
            @InputArgument List<AdditionalService> additionalServices,
            @InputArgument String customerComment,
            @InputArgument String executorComment,
            @InputArgument RedFlag redFlag,
            @InputArgument Float cost,
            @InputArgument Boolean archived,
            @InputArgument List<SpaceDto> rooms,
            @InputArgument String client,
            @InputArgument String brigade
    );

    @DgsMutation
    @Schema(description = "Обновить существующий заказ")
    OrderDto updateOrder(
            @InputArgument String uuid,
            @InputArgument CleaningType cleaningType,
            @InputArgument ExecutionStatus executionStatus,
            @InputArgument String execution,
            @InputArgument String createDate,
            @InputArgument String orderNumber,
            @InputArgument DistanceFromCenter distanceFromCenter,
            @InputArgument List<AdditionalService> additionalServices,
            @InputArgument String customerComment,
            @InputArgument String executorComment,
            @InputArgument RedFlag redFlag,
            @InputArgument Float cost,
            @InputArgument Boolean archived,
            @InputArgument List<SpaceDto> rooms,
            @InputArgument String client,
            @InputArgument String brigade
    );

    // @DgsMutation
    // @Schema(description = "Удалить заказ по ID")
    // Boolean deleteOrder(@InputArgument String uuid);
}
