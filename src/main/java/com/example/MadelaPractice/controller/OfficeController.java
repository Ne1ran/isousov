package com.example.MadelaPractice.controller;

import com.example.MadelaPractice.entity.OfficeEntity;
import com.example.MadelaPractice.model.OfficeSaveModel;
import com.example.MadelaPractice.model.OfficeUpdateModel;
import com.example.MadelaPractice.repository.OfficeRepo;
import com.example.MadelaPractice.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/office")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @PostMapping("/list/{orgId}")
    public ResponseEntity getOfficeListByOrgId(@PathVariable Long orgId, @RequestBody OfficeEntity officeEntity){
        try {
            return ResponseEntity.ok().body(officeService.getOfficeList(orgId, officeEntity));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error finding list of offices");
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
    public ResponseEntity updateOffice(@RequestBody OfficeUpdateModel model){
        try {
            officeService.updateOffice(model);
            return ResponseEntity.ok().body("Office updated!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error updating office");
        }
    }

    @PostMapping("/save")
    public ResponseEntity saveOffice(@RequestBody OfficeSaveModel model){
        try {
            officeService.saveNewOffice(model);
            return ResponseEntity.ok().body("Saved office!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error saving office");
        }
    }
}
