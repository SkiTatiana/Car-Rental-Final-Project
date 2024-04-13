package car.rental.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Embeddable
@Data
@NoArgsConstructor
public class Model {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long modelId;
	private String make;
	private String modelName;
	private Long numSeats;
	private String vehicleClass;
	private String vehicleType;

}
