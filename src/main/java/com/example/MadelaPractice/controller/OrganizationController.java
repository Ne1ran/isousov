package com.example.MadelaPractice.controller;

import com.example.MadelaPractice.entity.OrganizationEntity;
import com.example.MadelaPractice.repository.OrganizationRepo;
import com.example.MadelaPractice.repository.UserRepo;
import com.example.MadelaPractice.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organization")
public class OrganizationController {

    @Autowired
    private OrganizationRepo organizationRepo;

    @GetMapping("/list")
    public ResponseEntity getOrganizationsList(@RequestParam String name, @RequestParam String inn, @RequestParam Boolean isActive){
        try {
            return ResponseEntity.ok().body(organizationRepo.findAll());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOrganizationById(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(organizationRepo.findById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity updateOrganization(@RequestBody OrganizationEntity organizationEntity){
        try {
            organizationRepo.save(organizationEntity);
            return ResponseEntity.ok().body("Updated org!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity saveNewOrganization(@RequestBody OrganizationEntity organizationEntity){
        try {
            organizationRepo.save(organizationEntity);
            return ResponseEntity.ok().body("Saved org!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
