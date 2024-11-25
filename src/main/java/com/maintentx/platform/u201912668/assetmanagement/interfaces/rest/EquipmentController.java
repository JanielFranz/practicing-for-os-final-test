package com.maintentx.platform.u201912668.assetmanagement.interfaces.rest;

import com.maintentx.platform.u201912668.assetmanagement.domain.model.queries.GetAllEquipmentQuery;
import com.maintentx.platform.u201912668.assetmanagement.domain.model.queries.GetEquipmentByIdQuery;
import com.maintentx.platform.u201912668.assetmanagement.domain.services.EquipmentCommandService;
import com.maintentx.platform.u201912668.assetmanagement.domain.services.EquipmentQueryService;
import com.maintentx.platform.u201912668.assetmanagement.interfaces.rest.resources.CreateEquipmentResource;
import com.maintentx.platform.u201912668.assetmanagement.interfaces.rest.resources.EquipmentResource;
import com.maintentx.platform.u201912668.assetmanagement.interfaces.rest.transform.CreateEquipmentCommandFromResourceAssembler;
import com.maintentx.platform.u201912668.assetmanagement.interfaces.rest.transform.EquipmentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/equipment", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Equipment", description = "Operations related to equipment")
public class EquipmentController {
    private final EquipmentCommandService equipmentCommandService;
    private final EquipmentQueryService equipmentQueryService;

    public EquipmentController(EquipmentCommandService equipmentCommandService, EquipmentQueryService equipmentQueryService) {
        this.equipmentCommandService = equipmentCommandService;
        this.equipmentQueryService = equipmentQueryService;
    }


    @PostMapping
    @Operation(summary = "Create a new equipment")
    @ApiResponses( value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Equipment created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Equipment already exists")
    })

    public ResponseEntity<EquipmentResource> createEquipment(@RequestBody @Valid CreateEquipmentResource resource) {
        var createResourceCommand = CreateEquipmentCommandFromResourceAssembler.toCommandFromResouce(resource);

        var equipmentId = equipmentCommandService.handle(createResourceCommand);
        if(equipmentId == null || equipmentId == 0L) return ResponseEntity.badRequest().build();

        var getEquipmentByIdQuery = new GetEquipmentByIdQuery(equipmentId);
        var equipment = equipmentQueryService.handle(getEquipmentByIdQuery);

        if(equipment.isEmpty()) return ResponseEntity.notFound().build();

        var equipmentEntity = equipment.get();
        var equipmentResource = EquipmentResourceFromEntityAssembler.toResourceFromEntity(equipmentEntity);

        return new ResponseEntity<>(equipmentResource, HttpStatus.CREATED);

    }

    @GetMapping
    @Operation(summary = "Get all equipment")
    @ApiResponses( value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Equipment found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Equipment not found")
    })

    public ResponseEntity<List<EquipmentResource>> GetAllEquipments() {
        var getAllEquipmentsQuery = new GetAllEquipmentQuery();
        var equipments = equipmentQueryService.handle(getAllEquipmentsQuery);
        var equipmentResources = equipments.stream()
                .map(EquipmentResourceFromEntityAssembler::toResourceFromEntity).toList();

        return ResponseEntity.ok(equipmentResources);
    }
}
