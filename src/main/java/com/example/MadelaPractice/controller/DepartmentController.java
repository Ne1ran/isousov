package com.example.MadelaPractice.controller;

import com.example.MadelaPractice.dto.ApiResponse;
import com.example.MadelaPractice.entity.DepartmentEntity;
import com.example.MadelaPractice.model.DepartmentSaveModel;
import com.example.MadelaPractice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
@Tag(name = "Подразделения", description = "Подразделения организаций (1:N)")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/organization/{organizationId}")
    @Operation(summary = "Список подразделений организации")
    public ResponseEntity<ApiResponse<List<DepartmentEntity>>> listByOrg(@PathVariable Long organizationId) {
        return ResponseEntity.ok(ApiResponse.ok(departmentService.listByOrganization(organizationId)));
    }

    @PostMapping("/save")
    @Operation(summary = "Создание подразделения")
    public ResponseEntity<ApiResponse<DepartmentEntity>> save(@RequestBody @Valid DepartmentSaveModel model) {
        return ResponseEntity.ok(ApiResponse.ok(departmentService.create(model)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление подразделения")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("deleted"));
    }
}
