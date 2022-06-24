package com.example.MadelaPractice.controller;

import com.example.MadelaPractice.entity.OfficeEntity;
import com.example.MadelaPractice.model.OfficeListIn;
import com.example.MadelaPractice.model.OfficeListOut;
import com.example.MadelaPractice.model.OfficeSaveModel;
import com.example.MadelaPractice.model.OfficeUpdateModel;
import com.example.MadelaPractice.repository.OfficeRepo;
import com.example.MadelaPractice.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/office")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @PostMapping("/list")
    public ResponseEntity getOfficeListByOrgId(@RequestBody @Valid OfficeListIn model){
        try {
            return ResponseEntity.ok().body(officeService.getOfficeList(model).stream().map(OfficeListOut::toModel).collect(Collectors.toList()));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOfficeById(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(officeService.getOfficeById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error finding an office by its id");
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateOffice(@RequestBody @Valid OfficeUpdateModel model){
        try {
            officeService.updateOffice(model);
            return ResponseEntity.ok().body("Result: success!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error updating office");
        }
    }

    @PostMapping("/save")
    public ResponseEntity saveOffice(@RequestBody @Valid OfficeSaveModel model){
        try {
            officeService.saveNewOffice(model);
            return ResponseEntity.ok().body("Result: success!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error saving office");
        }
    }
}
