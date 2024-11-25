package com.maintentx.platform.u201912668.ordermanagement.interfaces.transform;

import com.maintentx.platform.u201912668.ordermanagement.domain.model.aggregates.ServiceOrder;
import com.maintentx.platform.u201912668.ordermanagement.interfaces.resources.ServiceOrderResource;

public class ServiceOrderResourceFromEntityAssembler {
    public static ServiceOrderResource toResourceFromEntity (ServiceOrder entity) {
        return new ServiceOrderResource(entity.getId(), entity.getTechnicianEmail(), entity.getPriority().name(),
                entity.getIssueDescription(), entity.getEquipmentCode().equipmentCode());
    }
}
