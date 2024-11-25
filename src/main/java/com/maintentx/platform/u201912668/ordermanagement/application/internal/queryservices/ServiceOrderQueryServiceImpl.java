package com.maintentx.platform.u201912668.ordermanagement.application.internal.queryservices;

import com.maintentx.platform.u201912668.ordermanagement.domain.model.aggregates.ServiceOrder;
import com.maintentx.platform.u201912668.ordermanagement.domain.model.queries.GetAllServiceOrdersByEquipmentCodeQuery;
import com.maintentx.platform.u201912668.ordermanagement.domain.model.queries.GetServiceOrderByIdQuery;
import com.maintentx.platform.u201912668.ordermanagement.domain.model.valueobjects.AssetEquipmentCode;
import com.maintentx.platform.u201912668.ordermanagement.domain.services.ServiceOrderQueryService;
import com.maintentx.platform.u201912668.ordermanagement.infrastructure.persistence.jpa.ServiceOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceOrderQueryServiceImpl implements ServiceOrderQueryService {
    private final ServiceOrderRepository serviceOrderRepository;

    public ServiceOrderQueryServiceImpl(ServiceOrderRepository serviceOrderRepository) {
        this.serviceOrderRepository = serviceOrderRepository;
    }

    @Override
    public List<ServiceOrder> handle(GetAllServiceOrdersByEquipmentCodeQuery query) {
        var equipmentCode = new AssetEquipmentCode(query.equipmentCode());
        return serviceOrderRepository.findAllByEquipmentCode(equipmentCode);
    }

    @Override
    public Optional<ServiceOrder> handle(GetServiceOrderByIdQuery query) {
        return serviceOrderRepository.findById(query.id());
    }
}
