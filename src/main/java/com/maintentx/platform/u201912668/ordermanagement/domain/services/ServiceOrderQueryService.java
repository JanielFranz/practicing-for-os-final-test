package com.maintentx.platform.u201912668.ordermanagement.domain.services;

import com.maintentx.platform.u201912668.ordermanagement.domain.model.aggregates.ServiceOrder;
import com.maintentx.platform.u201912668.ordermanagement.domain.model.queries.GetAllServiceOrdersByEquipmentCodeQuery;
import com.maintentx.platform.u201912668.ordermanagement.domain.model.queries.GetServiceOrderByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ServiceOrderQueryService {
    List<ServiceOrder> handle (GetAllServiceOrdersByEquipmentCodeQuery query);
    Optional<ServiceOrder> handle (GetServiceOrderByIdQuery query);
}
