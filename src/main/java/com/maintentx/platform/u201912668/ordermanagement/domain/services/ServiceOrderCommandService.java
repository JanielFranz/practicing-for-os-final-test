package com.maintentx.platform.u201912668.ordermanagement.domain.services;

import com.maintentx.platform.u201912668.ordermanagement.domain.model.commands.CreateServiceOrderCommand;

public interface ServiceOrderCommandService {
    Long handle (CreateServiceOrderCommand command);
}
