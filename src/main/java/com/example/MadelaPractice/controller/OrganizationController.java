package com.example.MadelaPractice.controller;

import com.example.MadelaPractice.entity.OrganizationEntity;
import com.example.MadelaPractice.model.OrganizationListIn;
import com.example.MadelaPractice.model.OrganizationListOut;
import com.example.MadelaPractice.model.OrganizationSaveModel;
import com.example.MadelaPractice.model.OrganizationUpdateModel;
import com.example.MadelaPractice.repository.OrganizationRepo;
import com.example.MadelaPractice.repository.UserRepo;
import com.example.MadelaPractice.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/organization")
public class OrganizationController {

    @Autowired OrganizationService organizationService;

    @PostMapping("/list")
    public ResponseEntity getOrganizationsList(@RequestBody @Valid OrganizationListIn organizationListIn){
        try {
            return ResponseEntity.ok().body(organizationService.getOrganizationsListByName(organizationListIn).stream().
                    map(OrganizationListOut::toModel).collect(Collectors.toList()));
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
    public ResponseEntity updateOrganization(@RequestBody @Valid OrganizationUpdateModel model){
        try {
            organizationService.updateOrganization(model);
            return ResponseEntity.ok().body("Updated org!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity saveNewOrganization(@RequestBody @Valid OrganizationSaveModel model){
        try {
            organizationService.saveOrganization(model);
            return ResponseEntity.ok().body("Saved org!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
