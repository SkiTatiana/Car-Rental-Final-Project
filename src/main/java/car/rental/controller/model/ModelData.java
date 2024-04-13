package car.rental.controller.model;

import car.rental.entity.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModelData {

	private Long modelId;
	private String make;
	private String modelName;
	private Long numSeats;
	private String vehicleClass;
	private String vehicleType;

	public ModelData(Model model) {
		modelId = model.getModelId();
		make = model.getMake();
		modelName = model.getModelName();
		numSeats = model.getNumSeats();
		vehicleClass = model.getVehicleClass();
		vehicleType = model.getVehicleType();

	}
}
