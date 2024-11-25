package com.maintentx.platform.u201912668.ordermanagement.domain.model.aggregates;

import com.maintentx.platform.u201912668.ordermanagement.domain.model.commands.CreateServiceOrderCommand;
import com.maintentx.platform.u201912668.ordermanagement.domain.model.valueobjects.AssetEquipmentCode;
import com.maintentx.platform.u201912668.ordermanagement.domain.model.valueobjects.Priority;
import com.maintentx.platform.u201912668.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ServiceOrder extends AuditableAbstractAggregateRoot<ServiceOrder> {

    @NotNull
    @Size(max = 500)
    private String technicianEmail;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @NotNull
    @Size(max = 500)
    private String issueDescription;

    @Embedded
    private AssetEquipmentCode equipmentCode;

    public ServiceOrder(CreateServiceOrderCommand command) {
        this.technicianEmail = command.technicianEmail();
        assignPriority(command.priority());
        this.issueDescription = command.issueDescription();
        this.equipmentCode = new AssetEquipmentCode(command.equipmentCode());
    }

    private void assignPriority(String priority) {
        switch(priority) {
            case "LOW": this.priority = Priority.LOW; break;
            case "MEDIUM": this.priority = Priority.MEDIUM; break;
            case "HIGH": this.priority = Priority.HIGH; break;
            case "CRITICAL": this.priority = Priority.CRITICAL; break;
            default: throw new IllegalArgumentException("Invalid priority");
        }
    }

}
