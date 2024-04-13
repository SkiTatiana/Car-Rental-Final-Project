package car.rental.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import car.rental.entity.Location;

public interface LocationDao extends JpaRepository<Location, Long> {

}
