package ro.fastrackit.trainsApp.service.route;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fastrackit.trainsApp.exception.ValidationException;
import ro.fastrackit.trainsApp.model.entity.Route;
import ro.fastrackit.trainsApp.repository.location.LocationRepository;
import ro.fastrackit.trainsApp.repository.train.TrainRepository;
import ro.fastrackit.trainsApp.service.train.TrainValidator;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

@Component
@RequiredArgsConstructor
public class RouteValidator {
    private final TrainRepository trainRepository;
    private final TrainValidator trainValidator;
    private final LocationRepository locationRepository;

    public void validateLocation(Route newRoute) {
        validateLocationRoute(newRoute.getStart())
                .or(() -> validateLocationRoute(newRoute.getDestination()))
                .or(() -> validateTrainLocation(newRoute))
                .ifPresent(ex -> {
                    throw ex;
                });
    }

    private Optional<ValidationException> validateTrainLocation(Route newRoute) {
        String locationName = newRoute.getStart();
        String trainId = newRoute.getAssignedTrainId();

        trainValidator.validateExistsOrThrow(trainId);

        return trainRepository.findById(trainId).orElseThrow().getLocation().equalsIgnoreCase(locationName)
                ? empty()
                : Optional.of(new ValidationException(List.of("Selected train must have the same location as your route start.")));
    }

    private Optional<ValidationException> validateLocationRoute(String location) {
        return locationRepository.existsByCity(location)
                ? empty()
                : Optional.of(new ValidationException(List.of("Both start and destination must be valid locations. " + location + " is not valid.")));
    }
}
