package ro.fastrackit.trainsApp.service.location;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fastrackit.trainsApp.model.entity.Location;
import ro.fastrackit.trainsApp.repository.location.LocationRepository;

@Service
@AllArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final LocationValidator locationValidator;

    public String getAll() {
        return locationRepository.findAll().toString();
    }

    public Location addLocation(Location newLocation) {
        locationValidator.validateNotExistsOrThrow(newLocation.getCity());
        locationRepository.save(newLocation);
        return newLocation;
    }

}
