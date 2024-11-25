package com.maintentx.platform.u201912668.assetmanagement.domain.services;

import com.maintentx.platform.u201912668.assetmanagement.domain.model.commands.CreateEquipmentCommand;

public interface EquipmentCommandService {
    Long handle (CreateEquipmentCommand command);
}
