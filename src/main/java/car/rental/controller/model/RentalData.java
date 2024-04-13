package car.rental.controller.model;

import java.time.LocalDate;

import car.rental.entity.Rental;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RentalData {

	public RentalData(Rental newRental) {
		this.startDate = newRental.getStartDate();
		this.endDate = newRental.getEndDate();
	}
	private LocalDate startDate;
	private LocalDate endDate;

}
