package com.maintentx.platform.u201912668.ordermanagement.interfaces;

import com.maintentx.platform.u201912668.ordermanagement.domain.model.queries.GetAllServiceOrdersByEquipmentCodeQuery;
import com.maintentx.platform.u201912668.ordermanagement.domain.model.queries.GetServiceOrderByIdQuery;
import com.maintentx.platform.u201912668.ordermanagement.domain.services.ServiceOrderCommandService;
import com.maintentx.platform.u201912668.ordermanagement.domain.services.ServiceOrderQueryService;
import com.maintentx.platform.u201912668.ordermanagement.interfaces.resources.CreateServiceOrderResource;
import com.maintentx.platform.u201912668.ordermanagement.interfaces.resources.ServiceOrderResource;
import com.maintentx.platform.u201912668.ordermanagement.interfaces.transform.CreateServiceOrderCommandFromResourceAssembler;
import com.maintentx.platform.u201912668.ordermanagement.interfaces.transform.ServiceOrderResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/service-orders", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Service Order", description = "Service Order Endpoints")
public class ServiceOrderController {
    private final ServiceOrderCommandService serviceOrderCommandService;
    private final ServiceOrderQueryService serviceOrderQueryService;

    public ServiceOrderController(ServiceOrderCommandService serviceOrderCommandService, ServiceOrderQueryService serviceOrderQueryService) {
        this.serviceOrderCommandService = serviceOrderCommandService;
        this.serviceOrderQueryService = serviceOrderQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new service order")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Service Order created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Service Order already exists")
    })


    public ResponseEntity<ServiceOrderResource> createServiceOrder(@RequestBody CreateServiceOrderResource resource) {
        var createServiceOrderCommand = CreateServiceOrderCommandFromResourceAssembler.toCommandFromResource(resource);
        var serviceOrderId = serviceOrderCommandService.handle(createServiceOrderCommand);
        if(serviceOrderId == null || serviceOrderId == 0L) return ResponseEntity.badRequest().build();

        var getServiceOrderByIdQuery = new GetServiceOrderByIdQuery(serviceOrderId);
        var serviceOrder = serviceOrderQueryService.handle(getServiceOrderByIdQuery);
        if(serviceOrder.isEmpty()) return ResponseEntity.badRequest().build();

        var serviceOrderEntity = serviceOrder.get();
        var responseEntityResource = ServiceOrderResourceFromEntityAssembler.toResourceFromEntity(serviceOrderEntity);
        return new ResponseEntity<>(responseEntityResource, HttpStatus.CREATED);
    }

    @GetMapping("/{equipmentCode}")
    @Operation(summary = "Get service orders by equipment code")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Service orders found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Service orders not found")
    })
    public ResponseEntity<List<ServiceOrderResource>> getServiceOrdersByEquipmentCode(@PathVariable String equipmentCode) {
        var getAllServiceOrdersByEquipmentCodeQuery = new GetAllServiceOrdersByEquipmentCodeQuery(equipmentCode);
        var serviceOrders = serviceOrderQueryService.handle(getAllServiceOrdersByEquipmentCodeQuery);
        var serviceOrderResources = serviceOrders.stream()
                .map(ServiceOrderResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(serviceOrderResources);
    }
}
