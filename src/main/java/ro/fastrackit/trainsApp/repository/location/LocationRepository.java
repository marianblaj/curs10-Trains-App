package ro.fastrackit.trainsApp.repository.location;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fastrackit.trainsApp.model.entity.Location;

public interface LocationRepository extends MongoRepository<Location, String> {
    boolean existsByCity(String city);
}
