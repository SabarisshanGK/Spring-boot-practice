package com.sabarisshan.Customers;

public record CustomerUpdateRequest(
        String name,
        int age,
        String email
) {
}
