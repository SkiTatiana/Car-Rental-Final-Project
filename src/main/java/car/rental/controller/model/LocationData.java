package car.rental.controller.model;

import car.rental.entity.Location;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationData {
	private Long locationId;
	private String locationName;
	private String address;
	private String city;
	private String state;
	private String zip;

	public LocationData(Location location) {
		locationId = location.getLocationId();
		locationName = location.getLocationName();
		address = location.getAddress();
		city = location.getCity();
		state = location.getState();
		zip = location.getZip();
	}
}
