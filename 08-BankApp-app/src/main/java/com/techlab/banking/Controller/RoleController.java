package com.techlab.banking.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.banking.entites.Role;
import com.techlab.banking.service.RoleService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/BankApp")
@RequiredArgsConstructor
public class RoleController {
 
    private final RoleService roleService;

    @PostMapping("/Addroles")
    public void addRole(@RequestBody Role role) {
        roleService.AddRole(role);
    }
}
