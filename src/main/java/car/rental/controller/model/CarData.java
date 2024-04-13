package car.rental.controller.model;

import java.util.HashSet;
import java.util.Set;

import car.rental.entity.Car;
import car.rental.entity.Location;
import car.rental.entity.Model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@NoArgsConstructor
public class CarData {
	private Long carId;
	private String vin;
	private String color;
	private Model model;
	private Location location;

	public CarData (Car car) {
		carId = car.getCarId();
		vin = car.getVin();
		color = car.getColor();
		model = car.getModel();
		location = car.getLocation();
		location.setCars(null);
/*
		@Data
		@NoArgsContructor
		static class LocationResponse {
			private Long locationId;
			private String locationName;
			private String address;
			private String city;
			private String state;
			private Long zip;

			private Set<Car> cars = new HashSet<>();

			LocationResponse (Location location) {
				locationId = location.getLocationId();
				locationName = location.getLocationName();
				address = location.getAddress();
				city = location.getCity();
				state = location.getState();
				zip = location.getZip();

				for ( Car car : location.getCars())	 {
					cars.add(car);
				}

			}*/
		}
	}
