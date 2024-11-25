package com.maintentx.platform.u201912668.ordermanagement.application.internal.outboundservices.acl;

import com.maintentx.platform.u201912668.assetmanagement.interfaces.acl.AssetManagementContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalAssetManagementService {
    private final AssetManagementContextFacade assetManagementContextFacade;

    public ExternalAssetManagementService(AssetManagementContextFacade assetManagementContextFacade) {
        this.assetManagementContextFacade = assetManagementContextFacade;
    }

    public boolean validateEquipmentCodeExistence(String equipmentCode) {
        return assetManagementContextFacade.isAssetEquipmentCodeValid(equipmentCode);
    }
}
