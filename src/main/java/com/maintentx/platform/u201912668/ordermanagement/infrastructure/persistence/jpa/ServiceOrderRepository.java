package com.maintentx.platform.u201912668.ordermanagement.infrastructure.persistence.jpa;

import com.maintentx.platform.u201912668.ordermanagement.domain.model.aggregates.ServiceOrder;
import com.maintentx.platform.u201912668.ordermanagement.domain.model.valueobjects.AssetEquipmentCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
    List<ServiceOrder> findAllByEquipmentCode(AssetEquipmentCode equipmentCode);
}
