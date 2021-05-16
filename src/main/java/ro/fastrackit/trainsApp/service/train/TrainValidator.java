package ro.fastrackit.trainsApp.service.train;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ro.fastrackit.trainsApp.exception.ResourceNotFoundException;
import ro.fastrackit.trainsApp.exception.ValidationException;
import ro.fastrackit.trainsApp.model.entity.Train;
import ro.fastrackit.trainsApp.repository.train.TrainRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

@Component
@RequiredArgsConstructor
public class TrainValidator {
    private final TrainRepository repo;

    private Optional<ValidationException> exists(String trainId) {
        return repo.existsById(trainId)
                ? empty()
                : Optional.of(new ValidationException(List.of("Id " + trainId + " does not exist.")));
    }

    public void validateExistsOrThrow(String trainId) {
        exists(trainId).ifPresent(ex -> {
            throw ex;
        });
    }

    @SneakyThrows
    public void validateModelChangeOrThrow(String trainId, Train patchedTrain) {
        Train dbTrain = repo.findById(trainId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn`t find train with id " + trainId));
        if (!dbTrain.getModel().equalsIgnoreCase(patchedTrain.getModel())) {
            throw new ValidationException(List.of("Model of the train cannot be modified"));
        }
    }
}
