package car.rental.service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import car.rental.controller.model.CustomerData;
import car.rental.controller.model.RentalData;
import car.rental.dao.CarDao;
import car.rental.dao.CustomerDao;
import car.rental.dao.RentalDao;
import car.rental.entity.Car;
import car.rental.entity.Customer;
import car.rental.entity.Rental;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CarDao carDao;

	@Autowired
	private RentalDao rentalDao;
	
	@Transactional(readOnly = false)
	public CustomerData saveCustomer(CustomerData customerData) {
		Long customerId = customerData.getCustomerId();
		Customer customer = findOrCreateCustomer(customerId);

		setFieldsInCustomer(customer, customerData);
		return new CustomerData(customerDao.save(customer));
	}

	private void setFieldsInCustomer(Customer customer, CustomerData customerData) {
		// customer.setCustomerId(customerData.getCustomerId);
		customer.setCustomerName(customerData.getCustomerName());
		customer.setPhone(customerData.getPhone());
		customer.setAddress(customerData.getAddress());

	}

	private Customer findCustomerById(Long customerId) {
		return customerDao.findById(customerId).orElseThrow();
	}

	private Customer findOrCreateCustomer(Long customerId) {
		Customer customer;

		if (Objects.isNull(customerId)) {
			customer = new Customer();
		} else {
			customer = findCustomerById(customerId);
		}
		return customer;
	}

	@Transactional(readOnly = true)
	public List<CustomerData> retrieveAllCustomers() {
		List<Customer> customers = customerDao.findAll();
		List<CustomerData> response = new LinkedList<CustomerData>();

		for (Customer customer : customers) {
			response.add(new CustomerData(customer));
		}

		return response;
	}

	@Transactional(readOnly = true)
	public CustomerData retrieveCustomerById(Long customerId) {
		Customer customer = findCustomerById(customerId);
		return new CustomerData(customer);
	}

	@Transactional(readOnly = false)
	public void deleteCustomerById(Long customerId) {
		Customer customer = findCustomerById(customerId);
		customerDao.delete(customer);
	}

	public RentalData insertRental(RentalData rentalData, Long customerId, Long carId) {
		Customer customer = findCustomerById(customerId);
		Car car = findCarById(carId);

		//customer.addCar(car, rentalData.getStartDate(), rentalData.getEndDate());
		//customerDao.save(customer);
		car.setRentals(new HashSet<>());
		customer.setRentals(new HashSet<>());
		Rental rental = new Rental();
		rental.setCar(car);
		rental.setCustomer(customer);
		rental.setCarId(car.getCarId());
		rental.setCustomerId(customer.getCustomerId());
		rental.setStartDate(rentalData.getStartDate());
		rental.setEndDate(rentalData.getEndDate());

		
		Rental newRental = rentalDao.save(rental);
		
		return new RentalData(newRental);
	}

	private Car findCarById(Long carId) {
		return carDao.findById(carId).orElseThrow();
	}

}
