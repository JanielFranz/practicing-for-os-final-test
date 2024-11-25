package com.maintentx.platform.u201912668.assetmanagement.infrastructure.persistence.jpa.repositories;

import com.maintentx.platform.u201912668.assetmanagement.domain.model.aggregates.Equipment;
import com.maintentx.platform.u201912668.assetmanagement.domain.model.valueobjects.EquipmentCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    boolean existsByEquipmentCode(EquipmentCode equipmentCode);
}
