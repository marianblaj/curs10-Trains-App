package ro.fastrackit.trainsApp.service.route;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.fastrackit.trainsApp.model.entity.Route;
import ro.fastrackit.trainsApp.repository.route.RouteRepository;

@Service
@AllArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final RouteValidator routeValidator;

    public Page<Route> getAll(Pageable pageable) {
        return routeRepository.findAll(pageable);
    }

    public Route addRoute(Route newRoute) {
        routeValidator.validateLocation(newRoute);
        return routeRepository.save(newRoute);
    }
}
