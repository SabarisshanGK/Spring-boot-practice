package com.sabarisshan.Customers;

import com.sabarisshan.Exception.DuplicateResource;
import com.sabarisshan.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerDAO customerDao;


    public CustomerService(@Qualifier("JPA") CustomerDAO customerDao){
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomersFromFakeDB(){
        return customerDao.getAllCustomers();
    }

    public Customer getCustomerFromGivenId(Integer id){
        return customerDao.getCustomerById(id).orElseThrow(()-> new ResourceNotFoundException("Customer with given id: [%s] is not found".formatted(id)));
    }

    public void addCustomer(CustomerRegistration customerRegistration){
        // Check CUstomer already with the email
        String email = customerRegistration.email();
        if(customerDao.existPersonWithEmail(email)){
            throw new DuplicateResource("Email already taken");
        }
        //add customer if email not exists
        Customer customer =  new Customer(
                customerRegistration.name(),
                customerRegistration.age(),
                customerRegistration.email()
        );
        customerDao.insertNewCustomer(customer);
    }

    // Function to Delete a Customer from Data base
    public void deleteCustomer(Integer id){
        if(customerDao.existCustomerWithID(id)){
            customerDao.deleteACustomerFromDB(id);
        }
        else {
            throw new ResourceNotFoundException("Already Deleted");
        }
    }
}
