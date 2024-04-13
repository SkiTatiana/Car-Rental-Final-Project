package car.rental.controller.model;

import java.util.HashSet;
import java.util.Set;

import car.rental.entity.Car;
import car.rental.entity.Customer;
import car.rental.entity.Rental;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerData {

	private Long customerId;
	private String customerName;
	private String phone;
	private String address;

	private Set<RentalData> rentals = new HashSet<>();
	private Set<CarData> cars = new HashSet<>();

	public CustomerData(Customer customer) {
		customerId = customer.getCustomerId();
		customerName = customer.getCustomerName();
		phone = customer.getPhone();
		address = customer.getAddress();

		for (Rental rental : customer.getRentals()) {
			rentals.add(new RentalData(rental));

		}
		for (Car car : customer.getCars()) {
			cars.add(new CarData(car));

		}

	}

}
