package com.maintentx.platform.u201912668.assetmanagement.interfaces.rest.transform;

import com.maintentx.platform.u201912668.assetmanagement.domain.model.commands.CreateEquipmentCommand;
import com.maintentx.platform.u201912668.assetmanagement.interfaces.rest.resources.CreateEquipmentResource;

public class CreateEquipmentCommandFromResourceAssembler {
    public static CreateEquipmentCommand toCommandFromResouce(CreateEquipmentResource resource) {
        return new CreateEquipmentCommand(resource.name(), resource.description());
    }
}
