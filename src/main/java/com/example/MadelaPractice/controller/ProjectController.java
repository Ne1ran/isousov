package com.example.MadelaPractice.controller;

import com.example.MadelaPractice.dto.ApiResponse;
import com.example.MadelaPractice.entity.ProjectEntity;
import com.example.MadelaPractice.model.IdListModel;
import com.example.MadelaPractice.model.ProjectSaveModel;
import com.example.MadelaPractice.model.TargetOrganizationModel;
import com.example.MadelaPractice.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@Tag(name = "Проекты", description = "Проекты организаций и участники (M:N с пользователями)")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/organization/{organizationId}")
    @Operation(summary = "Список проектов организации")
    public ResponseEntity<ApiResponse<List<ProjectEntity>>> listByOrg(@PathVariable Long organizationId) {
        return ResponseEntity.ok(ApiResponse.ok(projectService.listByOrganization(organizationId)));
    }

    @PostMapping("/save")
    @Operation(summary = "Создание проекта")
    public ResponseEntity<ApiResponse<ProjectEntity>> save(@RequestBody @Valid ProjectSaveModel model) {
        return ResponseEntity.ok(ApiResponse.ok(projectService.create(model)));
    }

    @PutMapping("/{id}/assignees")
    @Operation(summary = "Замена участников проекта (M:N)")
    public ResponseEntity<ApiResponse<ProjectEntity>> replaceAssignees(@PathVariable Long id,
                                                                         @RequestBody @Valid IdListModel body) {
        return ResponseEntity.ok(ApiResponse.ok(projectService.replaceAssignees(id, body)));
    }

    @PutMapping("/{id}/organization")
    @Operation(summary = "Перенос проекта в другую организацию")
    public ResponseEntity<ApiResponse<ProjectEntity>> moveOrg(@PathVariable Long id,
                                                                @RequestBody @Valid TargetOrganizationModel body) {
        return ResponseEntity.ok(ApiResponse.ok(projectService.moveToOrganization(id, body)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление проекта")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("deleted"));
    }
}
