package com.maintentx.platform.u201912668.ordermanagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record AssetEquipmentCode(String equipmentCode) {
    public AssetEquipmentCode {
        if (equipmentCode == null || equipmentCode.isBlank()) {
            throw new IllegalArgumentException("Equipment code cannot be null or empty");
        }
    }
}
