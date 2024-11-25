package com.maintentx.platform.u201912668.assetmanagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;


import java.util.UUID;

@Embeddable
public record EquipmentCode(String equipmentCode) {

    public EquipmentCode() {
        this(UUID.randomUUID().toString());
    }
}
