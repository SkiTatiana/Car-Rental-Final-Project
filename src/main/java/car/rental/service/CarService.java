package car.rental.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import car.rental.controller.model.CarData;
import car.rental.dao.*;
import car.rental.entity.*;

@Service
public class CarService {

	@Autowired
	private CarDao carDao;
	@Autowired
	private ModelDao modelDao;
	@Autowired
	private LocationDao locationDao;

	@Transactional(readOnly = false)
	public CarData saveCar(Long modelId, Long locationId, CarData carData) {
		Long carId = carData.getCarId();
		Car car = findOrCreateCar(carId, carData.getVin());

		setFieldsInCar(car, carData);

		Model model = modelDao.findById(modelId).orElseThrow(() -> new NoSuchElementException("Model with ID=" + modelId + " does not exist"));
		car.setModel(model);
		//model.getCars().add(car);

		Location location = locationDao.findById(locationId).orElseThrow(() -> new NoSuchElementException("Location with ID=" + locationId + " does not exist"));
		location.getCars().clear();
		car.setLocation(location);
		//location.getCars().add(car);
		
		return new CarData(carDao.save(car));
	}


	private void setFieldsInCar(Car car, CarData carData) {
		car.setColor(carData.getColor());
		car.setLocation(carData.getLocation());
		car.setModel(carData.getModel());
		car.setVin(carData.getVin());

	}

	private Car findCarById(Long carId) {
		return carDao.findById(carId).orElseThrow();
	}

	private Car findOrCreateCar(Long carId, String vin) {
		Car car;

		if (Objects.isNull(carId)) {
			Optional<Car> opCar = carDao.findByVin(vin);
			
		if(opCar.isPresent()) {
			throw new DuplicateKeyException("Car with Vin " + vin + " already exists.");
		}
			
			car = new Car();
		} else {
			car = findCarById(carId);
		}
		return car;
	}

	@Transactional(readOnly = true)
	public List<CarData> retrieveAllCars() {
		List<Car> cars = carDao.findAll();
		List<CarData> response = new LinkedList<CarData>();

		for (Car car : cars) {
			response.add(new CarData(car));
		}

		return response;
	}
	@Transactional (readOnly = true)
	public CarData retrieveCarById(Long carId) {
		Car car = findCarById (carId);
		return new CarData(car);
	}
	@Transactional (readOnly = false)
	public void deleteCarById(Long carId) {
		Car car = findCarById (carId);
		carDao.delete(car);
	}

}
