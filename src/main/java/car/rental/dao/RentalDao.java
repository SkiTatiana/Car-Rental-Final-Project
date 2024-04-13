package car.rental.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import car.rental.entity.*;

public interface RentalDao extends JpaRepository<Rental, RentalId>{

}
