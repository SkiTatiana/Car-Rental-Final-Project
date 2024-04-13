package car.rental.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Embeddable
@Data
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long customerId;
	private String customerName;
	private String phone;
	private String address;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = CascadeType.PERSIST)
	private Set<Rental> rentals = new HashSet<>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "rental", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "car_id"))
	private Set<Car> cars = new HashSet<>();

	public void addCar(Car car, LocalDate startDate, LocalDate endDate) {
		Rental rental = new Rental();
		rental.setCar(car);
		rental.setCustomer(this);
		rental.setCarId(car.getCarId());
		rental.setCustomerId(this.customerId);
		rental.setStartDate(startDate);
		rental.setEndDate(endDate);

		if (this.rentals == null)
			this.rentals = new HashSet<>();

		this.rentals.add(rental);

		if (car.getRentals() == null) {
			car.setRentals(new HashSet<>());
		}
		car.getRentals().add(rental);

	}
}
