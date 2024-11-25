package com.maintentx.platform.u201912668.assetmanagement.domain.services;

import com.maintentx.platform.u201912668.assetmanagement.domain.model.aggregates.Equipment;
import com.maintentx.platform.u201912668.assetmanagement.domain.model.queries.GetAllEquipmentQuery;
import com.maintentx.platform.u201912668.assetmanagement.domain.model.queries.GetEquipmentByIdQuery;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.Optional;

public interface EquipmentQueryService {
    Optional<Equipment> handle (GetEquipmentByIdQuery query);
    List<Equipment> handle (GetAllEquipmentQuery query);
}
