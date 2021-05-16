package ro.fastrackit.trainsApp.repository.route;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fastrackit.trainsApp.model.entity.Route;

public interface RouteRepository extends MongoRepository<Route, String> {

}
