package com.r3s.phonebook.controller;

import com.r3s.phonebook.model.request.ContactRequest;
import com.r3s.phonebook.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Operation(summary = "Add new contact")
    @PostMapping("/create")
    public ResponseEntity<?> createContact(@Valid @RequestBody ContactRequest inMsg) {
        Object object = contactService.createContact(inMsg);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Operation(summary = "Search Contact By Phone Number")
    @GetMapping("/getByPhoneNumber")
    ResponseEntity<?> getByPhoneNumber(@RequestParam String phoneNumber) {
        Object object = contactService.getByPhoneNumber(phoneNumber);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Operation(summary = "Search Contact By Group")
    @GetMapping("/getByGroup")
    ResponseEntity<?> getByGroup(@RequestParam String group) {
        Object object = contactService.getByGroup(group);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Operation(summary = "Update Contact",
            description = "set to null if you don't want to update")
    @PutMapping("/update")
    ResponseEntity<?> doUpdate(@Valid @RequestBody ContactRequest inMsg) {
        Object object = contactService.doUpdate(inMsg);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @Operation(summary = "Deleted Contact By Id")
    @DeleteMapping("/deletedById")
    ResponseEntity<?> deletedById(@RequestParam Long id) {
        Object object = contactService.deleteById(id);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

}
