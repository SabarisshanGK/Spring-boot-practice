package com.sabarisshan.Customers;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("JPA")
public class CustomerJPADataAccessService implements CustomerDAO{
    private final CustomerRepository customerRepository;

    public CustomerJPADataAccessService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public void insertNewCustomer(Customer customer) {
         customerRepository.save(customer);
    }

    @Override
    public boolean existPersonWithEmail(String email) {
        return customerRepository.existsCustomerByEmail(email);
    }

    @Override
    public void deleteACustomerFromDB(Integer id) {
            customerRepository.deleteById(id);
    }

    @Override
    public boolean existCustomerWithID(Integer id) {
        return customerRepository.existsCustomerById(id);
    }

    @Override
    public void updateCustomer(Customer update) {
        customerRepository.save(update);
    }




}
