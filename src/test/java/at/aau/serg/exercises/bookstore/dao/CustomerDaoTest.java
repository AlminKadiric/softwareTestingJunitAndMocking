package at.aau.serg.exercises.bookstore.dao;

import at.aau.serg.exercises.bookstore.dao.impl.ListCustomerDao;
import at.aau.serg.exercises.bookstore.entity.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CustomerDaoTest {

    private ListCustomerDao listCustomerDao = new ListCustomerDao();

    @Test
    public void testInsertCustomerDao() {
        Customer customer = new Customer(1L, "Alex", "Uni strasse");
        listCustomerDao.insert(customer);
        Assertions.assertEquals(1, listCustomerDao.findAll().size());


    }

    @Test
    public void findCustomerByNameTest() {
        Customer customer = new Customer(1L, "Alex", "Uni strasse");
        listCustomerDao.insert(customer);
        Customer customer1 = new Customer(2L, "Amica", "Unia");
        listCustomerDao.insert(customer1);
        List<Customer> nameCustomer = listCustomerDao.findCustomerByName("Amica");
        Assertions.assertEquals(1, nameCustomer.size());

    }

    @Test
    public void updateTest() {
        Customer customer = new Customer(1L, "Alex", "Uni strasse");
        listCustomerDao.insert(customer);
        Customer customer1 = new Customer(2L, "Amica", "Unia");
        listCustomerDao.insert(customer1);
        Customer customer2 = new Customer(2L, "Nedim", "Panda strasse");
        listCustomerDao.update(customer2);
        Assertions.assertEquals(2, listCustomerDao.findAll().size());
    }

    @Test
    public void deleteTest() {
        Customer t = new Customer(1L, "Almin", "Strasse 2");
        listCustomerDao.insert(t);
        Customer t2 = new Customer(2L, "Alex", "Strasse 1");
        listCustomerDao.insert(t2);
        listCustomerDao.delete(2L);
        Assertions.assertEquals(1, listCustomerDao.findAll().size());

    }
}
