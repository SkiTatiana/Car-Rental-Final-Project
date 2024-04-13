package car.rental.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import car.rental.controller.model.CarData;
import car.rental.controller.model.CustomerData;
import car.rental.controller.model.LocationData;
import car.rental.controller.model.ModelData;
import car.rental.controller.model.RentalData;
import car.rental.service.CarService;
import car.rental.service.CustomerService;
import car.rental.service.LocationService;
import car.rental.service.ModelService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/car_rental")
@Slf4j
public class CarRentalController {

	// ******* Location:
	@Autowired
	private LocationService locationService;

	@PostMapping("/location")
	@ResponseStatus(code = HttpStatus.CREATED)
	public LocationData insertLocation(@RequestBody LocationData locationData) {
		log.info("Creating location {}", locationData);
		return locationService.saveLocation(locationData);
	}

	@PutMapping("/location/{locationId}")
	public LocationData updateLocation(@PathVariable Long locationId, @RequestBody LocationData locationData) {
		locationData.setLocationId(locationId);
		log.info("Updating location {}", locationData);
		return locationService.saveLocation(locationData);
	}

	@GetMapping("/location")
	public List<LocationData> retrieveAllLocations() {
		log.info("Retreive all locations.");
		return locationService.retrieveAllLocations();

	}

	@GetMapping("/location/{locationId}")
	public LocationData retrieveLocationById(@PathVariable Long locationId) {
		log.info("Retrieving location with ID={}", locationId);
		return locationService.retrieveLocationById(locationId);

	}

	@DeleteMapping("/location")
	public void deleteAllLocations() {
		log.info("Attempting to delete all locations");
		throw new UnsupportedOperationException("Deleting all locations is not allowed.");
	}

	@DeleteMapping("/location/{locationId}")
	public Map<String, String> deleteLocationById(@PathVariable Long locationId) {
		log.info("Deleting location with ID={}", locationId);

		locationService.deleteLocationById(locationId);

		return Map.of("message", "Deletion of location with ID=" + locationId + " was successful.");
	}

//******* Car:
	@Autowired
	private CarService carService;

	@PostMapping("/model/{modelId}/location/{locationId}/car")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CarData insertCar(@PathVariable Long modelId, @PathVariable Long locationId, @RequestBody CarData carData) {
		log.info("Creating car {}", carData);
		return carService.saveCar(modelId, locationId, carData);
	}

	@PutMapping("/model/{modelId}/location/{locationId}/car/{carId}")
	public CarData updateCar(@PathVariable Long modelId, @PathVariable Long locationId, @PathVariable Long carId,
			@RequestBody CarData carData) {
		carData.setCarId(carId);
		log.info("Updating car {}", carData);
		return carService.saveCar(modelId, locationId, carData);
	}

	@GetMapping("/model/{modelId}/location/{locationId}/car")
	public List<CarData> retrieveAllCars() {
		log.info("Retreive all cars.");
		return carService.retrieveAllCars();

	}

	@GetMapping("/model/{modelId}/location/{locationId}/car/{carId}")
	public CarData retrieveCarById(@PathVariable Long carId) {
		log.info("Retrieving car with ID={}", carId);
		return carService.retrieveCarById(carId);

	}

	@DeleteMapping("/model/{modelId}/location/{locationId}/car")
	public void deleteAllCars() {
		log.info("Attempting to delete all cars");
		throw new UnsupportedOperationException("Deleting all cars is not allowed.");
	}

	@DeleteMapping("/model/{modelId}/location/{locationId}/car/{carId}")
	public Map<String, String> deleteCarById(@PathVariable Long carId) {
		log.info("Deleting car with ID={}", carId);

		carService.deleteCarById(carId);

		return Map.of("message", "Deletion of car with ID=" + carId + " was successful.");
	}

	// ************ Model:
	@Autowired
	private ModelService modelService;

	@PostMapping("/model")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ModelData insertModel(@RequestBody ModelData modelData) {
		log.info("Creating model {}", modelData);
		return modelService.saveModel(modelData);
	}

	@PutMapping("/model/{modelId}")
	public ModelData updateModel(@PathVariable Long modelId, @RequestBody ModelData modelData) {
		modelData.setModelId(modelId);
		log.info("Updating model {}", modelData);
		return modelService.saveModel(modelData);
	}

	@GetMapping("/model")
	public List<ModelData> retrieveAllModels() {
		log.info("Retrieve all models.");
		return modelService.retrieveAllModels();

	}

	@GetMapping("/model/{modelId}")
	public ModelData retrieveModelById(@PathVariable Long modelId) {
		log.info("Retrieving model with ID={}", modelId);
		return modelService.retrieveModelById(modelId);

	}

	@DeleteMapping("/model")
	public void deleteAllModels() {
		log.info("Attempting to delete all models.");
		throw new UnsupportedOperationException("Deleting all models is not allowed.");
	}

	@DeleteMapping("/model/{modelId}")
	public Map<String, String> deleteModelById(@PathVariable Long modelId) {
		log.info("Deleting model with ID={}", modelId);

		modelService.deleteModelById(modelId);

		return Map.of("message", "Deletion of model with ID=" + modelId + " was successful.");
	}
//****** Customer:

	@Autowired
	private CustomerService customerService;

	@PostMapping("/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CustomerData insertCustomer(@RequestBody CustomerData customerData) {
		log.info("Creating customer {}", customerData);
		return customerService.saveCustomer(customerData);
	}

	@PutMapping("/customer/{customerId}")
	public CustomerData updateCustomer(@PathVariable Long customerId, @RequestBody CustomerData customerData) {
		customerData.setCustomerId(customerId);
		log.info("Updating customer {}", customerData);
		return customerService.saveCustomer(customerData);
	}

	@GetMapping("/customer")
	public List<CustomerData> retrieveAllCustomers() {
		log.info("Retrieve all customers.");
		return customerService.retrieveAllCustomers();

	}

	@GetMapping("/customer/{customerId}")
	public CustomerData retrieveCustomerById(@PathVariable Long customerId) {
		log.info("Retrieving customer with ID={}", customerId);
		return customerService.retrieveCustomerById(customerId);

	}

	@DeleteMapping("/customer")
	public void deleteAllCustomers() {
		log.info("Attempting to delete all customers.");
		throw new UnsupportedOperationException("Deleting all customers is not allowed.");
	}

	@DeleteMapping("/customer/{customerId}")
	public Map<String, String> deleteCustomerById(@PathVariable Long customerId) {
		log.info("Deleting customer with ID={}", customerId);

		customerService.deleteCustomerById(customerId);

		return Map.of("message", "Deletion of customer with ID=" + customerId + " was successful.");
	}

	// ****** Rental:

	@PostMapping("/customer/{customerId}/car/{carId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RentalData insertRental(@RequestBody RentalData rentalData, @PathVariable Long customerId,
			@PathVariable Long carId) {
		log.info("Creating rental {}", rentalData);
		return customerService.insertRental(rentalData, customerId, carId);
	}
	/*
	 * @PutMapping("/customer/{customerId}") public CustomerData
	 * updateCustomer(@PathVariable Long customerId, @RequestBody CustomerData
	 * customerData) { customerData.setCustomerId(customerId);
	 * log.info("Updating customer {}", customerData); return
	 * customerService.saveCustomer(customerData); }
	 * 
	 * @GetMapping("/customer") public List<CustomerData> retrieveAllCustomers() {
	 * log.info("Retrieve all customers."); return
	 * customerService.retrieveAllCustomers();
	 * 
	 * }
	 * 
	 * @GetMapping("/customer/{customerId}") public CustomerData
	 * retrieveCustomerById(@PathVariable Long customerlId) {
	 * log.info("Retrieving customer with ID={}", customerlId); return
	 * customerService.retrieveCustomerById(customerlId);
	 * 
	 * }
	 * 
	 * @DeleteMapping("/customer") public void deleteAllCustomers() {
	 * log.info("Attempting to delete all customers."); throw new
	 * UnsupportedOperationException("Deleting all customers is not allowed."); }
	 * 
	 * @DeleteMapping("/customer/{customerId}") public Map<String, String>
	 * deleteCustomerById(@PathVariable Long customerId) {
	 * log.info("Deleting customer with ID={}", customerId);
	 * 
	 * customerService.deleteCustomerById(customerId);
	 * 
	 * return Map.of("message", "Deletion of customer with ID=" + customerId +
	 * " was successful."); }
	 */
}
