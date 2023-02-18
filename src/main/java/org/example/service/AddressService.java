package org.example.service;

import org.example.entity.Address;

public class AddressService {

    public String getAllViolations(Address address) {
        return ValidationService.getAllViolations(address);
    }
}
