package com.example.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.App;
import com.example.domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=App.class)
@Transactional
public class CustomerRepositoryTest {
	@Autowired
	CustomerRepository repository;

	@Before
	public void setUp() throws Exception {
		Customer cust = new Customer();
		cust.setFirstName("Hong");
		cust.setLastName("Lee");

		repository.save(cust);
		assertEquals(repository.count(), 5);
	}

	@Test
	public void test() {
//		fail("Not yet implemented");
	}

	@Test
	public void findAndUpdate() {
		List<Customer> list = repository.findAll();
		Customer customer = list.get(list.size() - 1);
		assertEquals(customer.getFirstName(), "Hong");
		customer.setFirstName("Park");
		repository.save(customer);
		Customer customer2 = repository.getOne(customer.getId());
		assertEquals(customer2.getFirstName(), customer.getFirstName());
	}

	@Test
	public void delete() {
		repository.deleteAll();
		assertEquals(repository.count(), 0);
	}
}
