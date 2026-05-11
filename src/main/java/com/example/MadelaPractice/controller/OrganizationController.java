package com.example.MadelaPractice.controller;

import com.example.MadelaPractice.dto.ApiResponse;
import com.example.MadelaPractice.model.OrganizationGetByIdModel;
import com.example.MadelaPractice.model.OrganizationListIn;
import com.example.MadelaPractice.model.OrganizationListOut;
import com.example.MadelaPractice.model.OrganizationSaveModel;
import com.example.MadelaPractice.model.OrganizationUpdateModel;
import com.example.MadelaPractice.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/organization")
@Tag(name = "Организации", description = "Создание, изменение, выборка и удаление организаций")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping
    @Operation(summary = "Список всех организаций")
    public ResponseEntity<ApiResponse<List<OrganizationListOut>>> getAllOrganizations() {
        List<OrganizationListOut> list = organizationService.getAllOrganizations().stream()
                .map(OrganizationListOut::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.ok(list));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление организации по id")
    public ResponseEntity<ApiResponse<String>> deleteOrganization(@PathVariable Long id) {
        organizationService.deleteOrganizationById(id);
        return ResponseEntity.ok(ApiResponse.ok("Result: success!"));
    }

    @PostMapping("/list")
    @Operation(summary = "Фильтрованный список организаций")
    public ResponseEntity<ApiResponse<List<OrganizationListOut>>> getOrganizationsList(
            @RequestBody @Valid OrganizationListIn organizationListIn) {
        List<OrganizationListOut> list = organizationService.getOrganizationsListByName(organizationListIn).stream()
                .map(OrganizationListOut::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.ok(list));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Организация по id")
    public ResponseEntity<ApiResponse<OrganizationGetByIdModel>> getOrganizationById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(organizationService.getOrgById(id)));
    }

    @PutMapping("/update")
    @Operation(summary = "Обновление организации")
    public ResponseEntity<ApiResponse<String>> updateOrganization(@RequestBody @Valid OrganizationUpdateModel model) {
        organizationService.updateOrganization(model);
        return ResponseEntity.ok(ApiResponse.ok("Result: success!"));
    }

    @PostMapping("/save")
    @Operation(summary = "Создание организации")
    public ResponseEntity<ApiResponse<String>> saveNewOrganization(@RequestBody @Valid OrganizationSaveModel model) {
        organizationService.saveOrganization(model);
        return ResponseEntity.ok(ApiResponse.ok("Result: success!"));
    }
}
