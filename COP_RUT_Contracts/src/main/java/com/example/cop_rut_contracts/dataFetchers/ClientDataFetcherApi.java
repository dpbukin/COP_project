package com.example.cop_rut_contracts.dataFetchers;

import com.example.cop_rut_contracts.dtos.base.ClientDto;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@DgsComponent
@Schema(description = "GraphQL API для работы с клиентами")
public interface ClientDataFetcherApi {

    @DgsQuery
    @Schema(description = "Получить список всех клиентов")
    List<ClientDto> clients();

    @DgsQuery
    @Schema(description = "Получить информацию о клиенте по ID")
    ClientDto clientById(@InputArgument String uuid);

    @DgsQuery
    @Schema(description = "Получить клиентов по номеру телефона")
    ClientDto clientsByPhoneNumber(@InputArgument String phone);

    @DgsMutation
    @Schema(description = "Создать нового клиента")
    ClientDto createClient(@InputArgument String name,
                           @InputArgument String phone,
                           @InputArgument String email,
                           @InputArgument List<String> orders);

    @DgsMutation
    @Schema(description = "Обновить информацию о клиенте")
    ClientDto updateClient(@InputArgument String uuid,
                           @InputArgument String name,
                           @InputArgument String phone,
                           @InputArgument String email,
                           @InputArgument List<String> orders);

}
