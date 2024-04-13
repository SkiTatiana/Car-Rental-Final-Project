package car.rental.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import car.rental.entity.Car;

public interface CarDao extends JpaRepository<Car, Long> {

	Optional<Car> findByVin(String vin);

}
