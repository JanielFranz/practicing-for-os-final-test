package com.maintentx.platform.u201912668.ordermanagement.interfaces.resources;

public record CreateServiceOrderResource(String technicianEmail, String priority,
                                         String issueDescription, String equipmentCode) {
}
