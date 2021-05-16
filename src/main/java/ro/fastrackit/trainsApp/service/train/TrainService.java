package ro.fastrackit.trainsApp.service.train;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ro.fastrackit.trainsApp.exception.ResourceNotFoundException;
import ro.fastrackit.trainsApp.model.TrainFilters;
import ro.fastrackit.trainsApp.model.entity.Train;
import ro.fastrackit.trainsApp.repository.train.TrainRepository;
import ro.fastrackit.trainsApp.repository.train.TrainDao;

@Service
@AllArgsConstructor
public class TrainService {
    private final TrainDao trainDao;
    private final TrainRepository repo;
    private final TrainValidator validator;
    private final ObjectMapper mapper;

    public Page<Train> getAll(TrainFilters trainFilters) {
        return trainDao.getAllWithFilters(trainFilters);
    }

    public Train addTrain(Train newTrain) {
        return repo.save(newTrain);
    }

    @SneakyThrows
    public Train patchTrain(JsonPatch patch, String trainId) {
        validator.validateExistsOrThrow(trainId);
        Train dbTrain = repo.findById(trainId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn`t find train with id " + trainId));
        JsonNode patchedTrainJson = patch.apply(mapper.valueToTree(dbTrain));
        Train patchedTrain = mapper.treeToValue(patchedTrainJson, Train.class);
        validator.validateModelChangeOrThrow(trainId, patchedTrain);
        return replaceTrain(trainId, patchedTrain);
    }

    private Train replaceTrain(String trainId, Train newTrain) {
        newTrain.setId(trainId);
        Train dbTrain = repo.findById(trainId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn`t find train with id " + trainId));
        copyTrain(newTrain, dbTrain);
        return repo.save(dbTrain);
    }

    private void copyTrain(Train newTrain, Train dbTrain) {
        dbTrain.setCarts(newTrain.getCarts());
        dbTrain.setLocation(newTrain.getLocation());
    }

    public void deleteTrain(String trainId) {
        validator.validateExistsOrThrow(trainId);
        repo.deleteById(trainId);
    }

}
