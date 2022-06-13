package com.example.MadelaPractice.controller;

import com.example.MadelaPractice.entity.OfficeEntity;
import com.example.MadelaPractice.repository.OfficeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/office")
public class OfficeController {

    @Autowired
    private OfficeRepo officeRepo;

    @GetMapping("/list/{orgId}")
    public ResponseEntity getOfficeListByOrgId(@PathVariable Long orgId, @RequestParam String name,
                                               @RequestParam String phone, @RequestParam Boolean isActive){
        try {
            return ResponseEntity.ok().body(officeRepo.findAllById(orgId)); //???
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error finding list of offices");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOfficeById(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(officeRepo.findById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error finding an office by its id");
        }
    }

    @PostMapping("/update")
    public ResponseEntity updateOffice(@RequestParam Long id, @RequestParam String name, @RequestParam String address,
                                       @RequestParam String phone, @RequestParam Boolean isActive){
        try {
            return ResponseEntity.ok().body("///");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error updating office");
        }
    }

    @PostMapping("/save")
    public ResponseEntity saveOffice(@RequestBody OfficeEntity officeEntity){
        try {
            return ResponseEntity.ok().body("Saved office!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error saving office");
        }
    }
}
