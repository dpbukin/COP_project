package com.example.cop_rut.service;

import com.example.cop_rut.dtos.base.BrigadeDto;

import java.util.List;

public interface BrigadeService {
//    void save(Brigade brigade);
    BrigadeDto add(BrigadeDto brigadeDto);
    BrigadeDto getBrigadeByUuid(String brigadeId);
    List<BrigadeDto> getAllBrigades();
    String freeBrigade(String brigadeUuid);
    BrigadeDto updateBrigade(BrigadeDto brigadeDto);
    void removeBrigadeByUuid(String brigadeId);
    String assignBrigadeToOrder(String orderId);
}
