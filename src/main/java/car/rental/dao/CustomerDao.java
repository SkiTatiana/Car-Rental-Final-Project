package car.rental.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import car.rental.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
