package com.maintentx.platform.u201912668.assetmanagement.application.acl;

import com.maintentx.platform.u201912668.assetmanagement.domain.model.valueobjects.EquipmentCode;
import com.maintentx.platform.u201912668.assetmanagement.infrastructure.persistence.jpa.repositories.EquipmentRepository;
import com.maintentx.platform.u201912668.assetmanagement.interfaces.acl.AssetManagementContextFacade;
import org.springframework.stereotype.Service;

@Service
public class AssetManagementContextFacadeImpl implements AssetManagementContextFacade {
    private final EquipmentRepository equipmentRepository;

    public AssetManagementContextFacadeImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public boolean isAssetEquipmentCodeValid(String equipmentCode) {

        var actualEquipmentCode = new EquipmentCode(equipmentCode);

        return equipmentRepository.existsByEquipmentCode(actualEquipmentCode);
    }
}
