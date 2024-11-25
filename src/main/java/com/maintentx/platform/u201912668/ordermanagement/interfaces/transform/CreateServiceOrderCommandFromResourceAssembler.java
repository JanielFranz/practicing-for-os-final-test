package com.maintentx.platform.u201912668.ordermanagement.interfaces.transform;

import com.maintentx.platform.u201912668.ordermanagement.domain.model.commands.CreateServiceOrderCommand;
import com.maintentx.platform.u201912668.ordermanagement.interfaces.resources.CreateServiceOrderResource;

public class CreateServiceOrderCommandFromResourceAssembler {
    public static CreateServiceOrderCommand toCommandFromResource(CreateServiceOrderResource resource) {
        return new CreateServiceOrderCommand(resource.technicianEmail(), resource.priority(), resource.issueDescription(), resource.equipmentCode());
    }
}
