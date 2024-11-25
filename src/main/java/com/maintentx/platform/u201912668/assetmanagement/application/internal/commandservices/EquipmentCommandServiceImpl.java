package com.maintentx.platform.u201912668.assetmanagement.application.internal.commandservices;

import com.maintentx.platform.u201912668.assetmanagement.domain.model.aggregates.Equipment;
import com.maintentx.platform.u201912668.assetmanagement.domain.model.commands.CreateEquipmentCommand;
import com.maintentx.platform.u201912668.assetmanagement.domain.services.EquipmentCommandService;
import com.maintentx.platform.u201912668.assetmanagement.infrastructure.persistence.jpa.repositories.EquipmentRepository;
import org.springframework.stereotype.Service;

@Service
public class EquipmentCommandServiceImpl implements EquipmentCommandService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentCommandServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Long handle(CreateEquipmentCommand command) {
        var equipment = new Equipment(command);
        try{
            equipmentRepository.save(equipment);
        }catch (Exception e) {
            throw new RuntimeException("Error while saving equipment: %s".formatted(e.getMessage()));
        }

        return equipment.getId();
    }
}
