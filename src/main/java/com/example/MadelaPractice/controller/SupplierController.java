package com.example.MadelaPractice.controller;

import com.example.MadelaPractice.dto.ApiResponse;
import com.example.MadelaPractice.entity.SupplierEntity;
import com.example.MadelaPractice.model.IdListModel;
import com.example.MadelaPractice.model.SupplierSaveModel;
import com.example.MadelaPractice.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@Tag(name = "Поставщики", description = "Поставщики и связь с офисами (M:N)")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    @Operation(summary = "Все поставщики")
    public ResponseEntity<ApiResponse<List<SupplierEntity>>> all() {
        List<SupplierEntity> list = new ArrayList<>();
        supplierService.findAll().forEach(list::add);
        return ResponseEntity.ok(ApiResponse.ok(list));
    }

    @PostMapping("/save")
    @Operation(summary = "Создание поставщика")
    public ResponseEntity<ApiResponse<SupplierEntity>> save(@RequestBody @Valid SupplierSaveModel model) {
        return ResponseEntity.ok(ApiResponse.ok(supplierService.create(model)));
    }

    @PutMapping("/{id}/offices")
    @Operation(summary = "Замена офисов поставщика (M:N)")
    public ResponseEntity<ApiResponse<SupplierEntity>> replaceOffices(@PathVariable Long id,
                                                                      @RequestBody @Valid IdListModel body) {
        return ResponseEntity.ok(ApiResponse.ok(supplierService.replaceOffices(id, body)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление поставщика")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("deleted"));
    }
}
