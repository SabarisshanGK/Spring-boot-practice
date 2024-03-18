package com.sabarisshan.Customers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("JDBC")
public class CustomerJDBCDataAccessService implements CustomerDAO{

    private  final JdbcTemplate jdbcTemplate;

    public CustomerJDBCDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> getAllCustomers() {
        var sql = """
                SELECT id,name,email,age FROM customer
                """;

        RowMapper<Customer> customerRowMapper = (rs, rowNum) -> {
            Customer customer = new Customer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("email")
            );
            return customer;
        };
        List<Customer> customers = jdbcTemplate.query(sql,customerRowMapper);
        return  customers;
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        var sql = """
                SELECT id,name,age,email FROM customer WHERE id = ?
                """;
        RowMapper<Customer> customerRowMapper = (rs, rowNum) -> {
            Customer customer = new Customer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("email")
            );
            return customer;
        };
        return  jdbcTemplate.query(sql,customerRowMapper,id).stream().findFirst();
    }

    @Override
    public void insertNewCustomer(Customer customer) {
        var sql = """
                INSERT INTO customer(name,age,email)
                VALUES(?,?,?)
                """;
        int result = jdbcTemplate.update(sql,customer.getName(),customer.getAge(),customer.getEmail());
        System.out.println("Jdbc Update: "+result);
    }

    @Override
    public boolean existPersonWithEmail(String email) {
        var sql = """
                SELECT count(id) FROM customer WHERE email = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class,email);
        return count!=null && count>0;
    }

    @Override
    public void deleteACustomerFromDB(Integer id) {
        var sql = """
                DELETE FROM customer WHERE id =?
                """;
        int result = jdbcTemplate.update(sql,id);
        System.out.println(result + " Row Deleted");
    }

    @Override
    public boolean existCustomerWithID(Integer id) {
        var sql = """
                SELECT count(id) FROM customer WHERE id = ?
                """;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class,id);
        return count!=null && count>0;
    }

    @Override
    public void updateCustomer(Customer update) {
        if(update.getName() != null){
            var sql = """
                    UPDATE customer SET name = ? WHERE id = ?
                    """;
            int result = jdbcTemplate.update(sql,update.getName(),update.getId());
            System.out.println(result+"+ Row Updated");
        }
        if(update.getAge() != 0){
            var sql = """
                    UPDATE customer SET age = ? WHERE id = ?
                    """;
            int result = jdbcTemplate.update(sql,update.getAge(),update.getId());
            System.out.println(result+"+ Row Updated");
        }
        if( update.getEmail() != null ){
            var sql = """
                    UPDATE customer SET email = ? WHERE id =?
                    """;
            int result = jdbcTemplate.update(sql,update.getEmail(),update.getId());
            System.out.println(result+"+ Row Updated");
        }
    }
}
