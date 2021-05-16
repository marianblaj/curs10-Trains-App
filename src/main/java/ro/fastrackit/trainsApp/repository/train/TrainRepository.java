package ro.fastrackit.trainsApp.repository.train;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.fastrackit.trainsApp.model.entity.Train;

@Repository
public interface TrainRepository extends MongoRepository<Train, String> {
     boolean existsByLocation(String location);
}
