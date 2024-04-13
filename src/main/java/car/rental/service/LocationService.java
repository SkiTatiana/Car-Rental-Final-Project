package car.rental.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import car.rental.controller.model.LocationData;
import car.rental.dao.LocationDao;
import car.rental.entity.Location;

@Service
public class LocationService {

	@Autowired
	private LocationDao locationDao;

	@Transactional(readOnly = false)
	public LocationData saveLocation(LocationData locationData) {
		Long locationId = locationData.getLocationId();
		Location location = findOrCreateLocation(locationId);

		setFieldsInLocation(location, locationData);
		return new LocationData(locationDao.save(location));
	}

	private void setFieldsInLocation(Location location, LocationData locationData) {
		// location.setLocationId(locationData.getLocationId());
		location.setLocationName(locationData.getLocationName());
		location.setAddress(locationData.getAddress());
		location.setCity(locationData.getCity());
		location.setState(locationData.getState());
		location.setZip(locationData.getZip());
	}

	private Location findOrCreateLocation(Long locationId) {
		Location location;

		if (Objects.isNull(locationId)) {
			location = new Location();
		} else {
			location = findLocationById(locationId);
		}

		return location;
	}

	private Location findLocationById(Long locationId) {
		return locationDao.findById(locationId).orElseThrow();
		// TODO () -> new NoSuchElementException("Location with ID=" + locationId + "
		// does not exist."));
	}

	@Transactional(readOnly = true)
	public List<LocationData> retrieveAllLocations() {
		List<Location> locations = locationDao.findAll();
		List<LocationData> response = new LinkedList<LocationData>();

		for (Location location : locations) {
			response.add(new LocationData(location));
		}

		return response;
	}

	@Transactional(readOnly = true)
	public LocationData retrieveLocationById(Long locationId) {
		Location location = findLocationById(locationId);
		return new LocationData(location);
	}

	@Transactional(readOnly = false)
	public void deleteLocationById(Long locationId) {
		Location location = findLocationById(locationId);
		locationDao.delete(location);

	}

}
