package com.maintentx.platform.u201912668.assetmanagement.interfaces.rest.transform;

import com.maintentx.platform.u201912668.assetmanagement.domain.model.aggregates.Equipment;
import com.maintentx.platform.u201912668.assetmanagement.interfaces.rest.resources.EquipmentResource;

public class EquipmentResourceFromEntityAssembler {
    public static EquipmentResource toResourceFromEntity(Equipment entity) {
        return new EquipmentResource(entity.getId(),
                entity.getEquipmentCode().equipmentCode(), entity.getName(), entity.getDescription(),
                entity.getStatus().name());
    }
}
