package ro.fastrackit.trainsApp.service.location;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fastrackit.trainsApp.exception.ValidationException;
import ro.fastrackit.trainsApp.repository.location.LocationRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

@Component
@RequiredArgsConstructor
public class LocationValidator {
    private final LocationRepository locationRepository;

    public void validateNotExistsOrThrow(String locationName) {
        exists(locationName).ifPresent(ex -> {
            throw ex;
        });
    }

    private Optional<ValidationException> exists(String locationName) {
        return locationRepository.existsByCity(locationName)
                ? Optional.of(new ValidationException(List.of("City " + locationName + " already exists.")))
                : empty();
    }
}
