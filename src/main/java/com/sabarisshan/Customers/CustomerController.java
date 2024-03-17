package com.sabarisshan.Customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("api/customers")
    List<Customer> getAllCustomers(){
        return customerService.getAllCustomersFromFakeDB();
    }

    @GetMapping("api/customers/{id}")
    Customer getCustomerbyId(@PathVariable("id") Integer id){
        return  customerService.getCustomerFromGivenId(id);
    }

    @PostMapping("api/add-new-customer")
    void RegisterNewCustomer(@RequestBody CustomerRegistration request){
        customerService.addCustomer(request);
    }

    @DeleteMapping("api/delete-customer/{id}")
    void DeleteCustomer(@PathVariable("id") Integer id){
        customerService.deleteCustomer(id);
    }

    @PutMapping("api/update-customer/{id}")
    void updateCustomer(@PathVariable("id") Integer id, @RequestBody CustomerUpdateRequest customerUpdateRequest){
        customerService.updateCustomer(customerUpdateRequest,id);
    }
}
