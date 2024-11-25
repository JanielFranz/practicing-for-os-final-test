package com.maintentx.platform.u201912668.ordermanagement.interfaces.resources;

public record ServiceOrderResource(Long id, String technicianEmail, String priority,
                                   String issueDescription, String equipmentCode) {
}
