package com.example.cop_rut_contracts.dataFetchers;

import com.example.cop_rut_contracts.dtos.base.BrigadeDto;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;


//TODO поправить названия или схему graphQL
@DgsComponent
@Schema(description = "GraphQL API для работы с бригадами")
public interface BrigadeDataFetcherApi {

    @DgsQuery
    @Schema(description = "Получить информацию о бригаде по ID")
    BrigadeDto getBrigadeById(@InputArgument String uuid);

    @DgsQuery
    @Schema(description = "Получить список всех бригад")
    List<BrigadeDto> getAllBrigades();

    @DgsMutation
    @Schema(description = "Создать новую бригаду")
    BrigadeDto createBrigade(@InputArgument String phone,
                                 @InputArgument List<String> workers,
                                 @InputArgument Boolean freeness,
                                 @InputArgument String order);

    @DgsMutation
    @Schema(description = "Обновить информацию о существующей бригаде")
    BrigadeDto updateBrigade(@InputArgument String uuid,
                             @InputArgument String phone,
                             @InputArgument List<String> workers,
                             @InputArgument Boolean freeness,
                             @InputArgument String order);
}
