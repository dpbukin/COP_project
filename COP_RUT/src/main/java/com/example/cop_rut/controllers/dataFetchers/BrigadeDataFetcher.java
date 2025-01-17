package com.example.cop_rut.controllers.dataFetchers;

import com.example.cop_rut.dtos.base.BrigadeDto;
import com.example.cop_rut.service.BrigadeService;
import com.example.cop_rut.service.WorkerService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@DgsComponent
public class BrigadeDataFetcher {

    private BrigadeService brigadeService;
    private WorkerService workerService;

    @DgsQuery
    public BrigadeDto getBrigadeById(@InputArgument String uuid) {
        return brigadeService.getBrigadeByUuid(uuid);
    }
    @DgsQuery
    public List<BrigadeDto> getAllBrigades() {
        return brigadeService.getAllBrigades();
    }
    @DgsMutation
    public BrigadeDto createBrigade(@InputArgument String phone,
                                    @InputArgument List<String> workers,
                                    @InputArgument Boolean freeness,
                                    @InputArgument String order) {

        List<String> workerList = new ArrayList<>();

        if(workers != null) {
            {
                for (String worker : workers) {
                    if (workerService.getWorkerByUuid(worker) == null) {
                        workerList = null;
                        break;
                    } else {
                        workerList.add(worker);
                    }
                }
            }
        }

        BrigadeDto brigadeDto = new BrigadeDto();
        brigadeDto.setPhoneNumber(phone);
        brigadeDto.setWorkersId(workerList);
        brigadeDto.setFreeness(freeness);
        if(workerService != null){
            brigadeDto.setOrder(order);
        }


        return brigadeService.add(brigadeDto);
    }
    @DgsMutation
    public BrigadeDto updateBrigade(@InputArgument String uuid,
                                    @InputArgument String phone,
                                    @InputArgument List<String> workers,
                                    @InputArgument Boolean freeness,
                                    @InputArgument String order) {

        List<String> workerList = new ArrayList<>();

        for(String worker: workers){
            if(workerService.getWorkerByUuid(worker) == null){
                workerList = null;
                break;
            }else{
                workerList.add(worker);
            }
        }

        BrigadeDto brigadeDto = new BrigadeDto();
        brigadeDto.setUuid(uuid);
        brigadeDto.setPhoneNumber(phone);
        brigadeDto.setWorkersId(workerList);
        brigadeDto.setFreeness(freeness);
        brigadeDto.setOrder(order);

        return brigadeService.updateBrigade(brigadeDto);
    }

    @Autowired
    public void setBrigadeService(BrigadeService brigadeService) {
        this.brigadeService = brigadeService;
    }

    @Autowired
    public void setWorkerService(WorkerService workerService) {
        this.workerService = workerService;
    }
}
