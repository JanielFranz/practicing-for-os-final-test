package com.maintentx.platform.u201912668.ordermanagement.domain.model.commands;

public record CreateServiceOrderCommand(String technicianEmail, String priority,
                                        String issueDescription, String equipmentCode) {
}
