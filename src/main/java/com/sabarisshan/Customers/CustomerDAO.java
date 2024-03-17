package com.sabarisshan.Customers;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Integer id);
    void insertNewCustomer(Customer customer);
    boolean existPersonWithEmail(String email);
    void deleteACustomerFromDB(Integer id);
    boolean existCustomerWithID(Integer id);
    void updateCustomer(Customer update);

}
