package com.example.MadelaPractice.controller;

import com.example.MadelaPractice.dto.ApiResponse;
import com.example.MadelaPractice.entity.OfficeEntity;
import com.example.MadelaPractice.model.OfficeGetModel;
import com.example.MadelaPractice.model.OfficeListIn;
import com.example.MadelaPractice.model.OfficeListOut;
import com.example.MadelaPractice.model.OfficeSaveModel;
import com.example.MadelaPractice.model.OfficeUpdateModel;
import com.example.MadelaPractice.model.TargetOrganizationModel;
import com.example.MadelaPractice.service.OfficeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/office")
@Tag(name = "Офисы", description = "Операции с офисами организаций")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @GetMapping
    @Operation(summary = "Список всех офисов")
    public ResponseEntity<ApiResponse<List<OfficeListOut>>> getAllOffices() {
        List<OfficeListOut> list = officeService.getAllOffices().stream()
                .map(OfficeListOut::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.ok(list));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление офиса по id")
    public ResponseEntity<ApiResponse<String>> deleteOffice(@PathVariable Long id) {
        officeService.deleteOfficeById(id);
        return ResponseEntity.ok(ApiResponse.ok("Result: success!"));
    }

    @PostMapping("/list")
    @Operation(summary = "Фильтрованный список офисов по организации")
    public ResponseEntity<ApiResponse<List<OfficeListOut>>> getOfficeListByOrgId(@RequestBody @Valid OfficeListIn model) {
        List<OfficeListOut> list = officeService.getOfficeList(model).stream()
                .map(OfficeListOut::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.ok(list));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Офис по id")
    public ResponseEntity<ApiResponse<OfficeGetModel>> getOfficeById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(officeService.getOfficeById(id)));
    }

    @PutMapping("/update")
    @Operation(summary = "Обновление офиса")
    public ResponseEntity<ApiResponse<String>> updateOffice(@RequestBody @Valid OfficeUpdateModel model) {
        officeService.updateOffice(model);
        return ResponseEntity.ok(ApiResponse.ok("Result: success!"));
    }

    @PostMapping("/save")
    @Operation(summary = "Создание офиса")
    public ResponseEntity<ApiResponse<String>> saveOffice(@RequestBody @Valid OfficeSaveModel model) {
        officeService.saveNewOffice(model);
        return ResponseEntity.ok(ApiResponse.ok("Result: success!"));
    }

    @PutMapping("/{id}/move-organization")
    @Operation(summary = "Перенос офиса в другую организацию")
    public ResponseEntity<ApiResponse<OfficeEntity>> moveOffice(
            @PathVariable Long id,
            @RequestBody @Valid TargetOrganizationModel body) {
        return ResponseEntity.ok(ApiResponse.ok(officeService.moveOfficeToOrganization(id, body)));
    }
}
