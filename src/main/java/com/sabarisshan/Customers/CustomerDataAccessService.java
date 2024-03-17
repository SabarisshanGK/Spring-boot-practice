package com.sabarisshan.Customers;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("List")
public class CustomerDataAccessService implements CustomerDAO{

    private static final List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer Sabari = new Customer("Sabarisshan",23,"sabarisshanemail");
        customers.add(Sabari);
        Customer Visva = new Customer("Visva",19,"visvaemail");
        customers.add(Visva);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        return customers.stream().filter(c -> c.getId() == id).findFirst();
    }

    @Override
    public void insertNewCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existPersonWithEmail(String email) {
        return customers.stream().anyMatch( c -> c.getEmail().equals(email));
    }

    @Override
    public void deleteACustomerFromDB(Integer id) {
        //customers.remove(id);
    }

    @Override
    public boolean existCustomerWithID(Integer id) {
        return false;
    }

    @Override
    public void updateCustomer(Customer update) {

    }
}
