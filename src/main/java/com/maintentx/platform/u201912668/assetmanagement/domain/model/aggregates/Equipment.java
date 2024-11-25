package com.maintentx.platform.u201912668.assetmanagement.domain.model.aggregates;

import com.maintentx.platform.u201912668.assetmanagement.domain.model.commands.CreateEquipmentCommand;
import com.maintentx.platform.u201912668.assetmanagement.domain.model.valueobjects.EquipmentCode;
import com.maintentx.platform.u201912668.assetmanagement.domain.model.valueobjects.EquipmentStatus;
import com.maintentx.platform.u201912668.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Equipment extends AuditableAbstractAggregateRoot<Equipment> {
    @NotNull
    @Embedded
    private EquipmentCode equipmentCode;

    @NotNull(message = "Name must not be null")
    @Size(max = 50)
    private String name;

    @NotNull(message = "Description must not be null")
    @Size(max = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EquipmentStatus status;

    public Equipment(CreateEquipmentCommand command) {
        this.equipmentCode = new EquipmentCode();
        this.name = command.name();
        this.description = command.description();
        this.status = EquipmentStatus.PENDING_SETUP;
    }


}
