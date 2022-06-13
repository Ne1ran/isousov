package com.example.MadelaPractice.controller;

import com.example.MadelaPractice.entity.OrganizationEntity;
import com.example.MadelaPractice.repository.OrganizationRepo;
import com.example.MadelaPractice.repository.UserRepo;
import com.example.MadelaPractice.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/organization")
public class OrganizationController {

    @Autowired OrganizationService organizationService;

    @GetMapping("/list")
    public ResponseEntity getOrganizationsList(@RequestBody OrganizationEntity organizationEntity){
        try {
            return ResponseEntity.ok().body(organizationService.getOrganizationsListByName(organizationEntity));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOrganizationById(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(organizationService.getOrgById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateOrganization(@RequestBody @Valid OrganizationEntity organizationEntity){
        try {
            organizationService.updateOrganization(organizationEntity);
            return ResponseEntity.ok().body("Updated org!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity saveNewOrganization(@RequestBody @Valid OrganizationEntity organizationEntity){
        try {
            organizationService.saveOrganization(organizationEntity);
            return ResponseEntity.ok().body("Saved org!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
