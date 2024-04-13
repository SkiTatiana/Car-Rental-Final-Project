package car.rental.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//@Embeddable
public class RentalId implements Serializable {
	//@Column(name = "car_id")
	private Long carId;
	//@Column(name = "customer_id")
	private Long customerId;
	
	public int hashCode() {
		return (int) ((Objects.isNull(carId) ? 0 : carId) + (Objects.isNull(customerId) ? 0 : customerId));
	}

	public boolean equals(Object object) {
		if(object instanceof RentalId) {
			RentalId otherId = (RentalId) object;
			return (Objects.isNull(otherId.carId) && Objects.isNull(this.carId) || otherId.carId == this.carId)
					&& (Objects.isNull(otherId.customerId) && Objects.isNull(this.customerId) || otherId.customerId == this.customerId); 
		}

		return false;
	}
}
