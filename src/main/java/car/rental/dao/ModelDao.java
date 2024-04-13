package car.rental.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import car.rental.entity.Model;

public interface ModelDao extends JpaRepository<Model, Long> {

}
