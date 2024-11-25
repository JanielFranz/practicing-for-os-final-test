package com.maintentx.platform.u201912668.assetmanagement.application.internal.queryservices;

import com.maintentx.platform.u201912668.assetmanagement.domain.model.aggregates.Equipment;
import com.maintentx.platform.u201912668.assetmanagement.domain.model.queries.GetAllEquipmentQuery;
import com.maintentx.platform.u201912668.assetmanagement.domain.model.queries.GetEquipmentByIdQuery;
import com.maintentx.platform.u201912668.assetmanagement.domain.services.EquipmentQueryService;
import com.maintentx.platform.u201912668.assetmanagement.infrastructure.persistence.jpa.repositories.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentQueryServiceImpl implements EquipmentQueryService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentQueryServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Optional<Equipment> handle(GetEquipmentByIdQuery query) {
        return equipmentRepository.findById(query.id());
    }

    @Override
    public List<Equipment> handle(GetAllEquipmentQuery query) {
        return equipmentRepository.findAll();
    }
}
