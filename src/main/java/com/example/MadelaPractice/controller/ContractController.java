package com.example.MadelaPractice.controller;

import com.example.MadelaPractice.dto.ApiResponse;
import com.example.MadelaPractice.entity.ContractEntity;
import com.example.MadelaPractice.model.ContractSaveModel;
import com.example.MadelaPractice.service.ContractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/contracts")
@Tag(name = "Договоры", description = "Договоры организаций (1:N к организации)")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping("/organization/{organizationId}")
    @Operation(summary = "Список договоров организации")
    public ResponseEntity<ApiResponse<List<ContractEntity>>> listByOrg(@PathVariable Long organizationId) {
        return ResponseEntity.ok(ApiResponse.ok(contractService.listByOrganization(organizationId)));
    }

    @PostMapping("/save")
    @Operation(summary = "Создание договора (каскад через организацию)")
    public ResponseEntity<ApiResponse<ContractEntity>> save(@RequestBody @Valid ContractSaveModel model) {
        return ResponseEntity.ok(ApiResponse.ok(contractService.create(model)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление договора")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        contractService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("deleted"));
    }
}
