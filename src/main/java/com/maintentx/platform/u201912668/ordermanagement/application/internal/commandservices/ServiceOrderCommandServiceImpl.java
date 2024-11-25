package com.maintentx.platform.u201912668.ordermanagement.application.internal.commandservices;

import com.maintentx.platform.u201912668.ordermanagement.application.internal.outboundservices.acl.ExternalAssetManagementService;
import com.maintentx.platform.u201912668.ordermanagement.domain.model.aggregates.ServiceOrder;
import com.maintentx.platform.u201912668.ordermanagement.domain.model.commands.CreateServiceOrderCommand;
import com.maintentx.platform.u201912668.ordermanagement.domain.services.ServiceOrderCommandService;
import com.maintentx.platform.u201912668.ordermanagement.infrastructure.persistence.jpa.ServiceOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceOrderCommandServiceImpl implements ServiceOrderCommandService {
    private final ServiceOrderRepository serviceOrderRepository;
    private final ExternalAssetManagementService externalAssetManagementService;

    public ServiceOrderCommandServiceImpl(ServiceOrderRepository serviceOrderRepository, ExternalAssetManagementService externalAssetManagementService) {
        this.serviceOrderRepository = serviceOrderRepository;
        this.externalAssetManagementService = externalAssetManagementService;
    }


    @Override
    public Long handle(CreateServiceOrderCommand command) {
        var equipmentCode = command.equipmentCode();
        if(!externalAssetManagementService.validateEquipmentCodeExistence(equipmentCode))
            throw new IllegalArgumentException("Equipment code does not exist");

        var serviceOrder = new ServiceOrder(command);

        try {
            serviceOrderRepository.save(serviceOrder);
        }catch(Exception e) {
            throw new RuntimeException("Error while creating service order: %s".formatted(e.getMessage()));
        }

        return serviceOrder.getId();
    }
}
