package ro.fastrackit.trainsApp.loader;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.fastrackit.trainsApp.repository.location.LocationRepository;
import ro.fastrackit.trainsApp.repository.route.RouteRepository;
import ro.fastrackit.trainsApp.repository.train.TrainRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final TrainRepository trainRepository;
    private final LocationRepository locationRepository;
    private final RouteRepository routeRepository;

    @Override
    public void run(String... args) throws Exception {
//        trainRepository.saveAll(List.of(
//                Train.builder()
//                        .model("Personal")
//                        .carts(10)
//                        .locationId("60a1472ddcd9f05aac2e086a")
//                .build(),
//                Train.builder()
//                        .model("Accelerat")
//                        .carts(12)
//                        .locationId("60a1472ddcd9f05aac2e086b")
//                        .build(),
//                Train.builder()
//                        .model("Rapid")
//                        .carts(8)
//                        .locationId("60a1472ddcd9f05aac2e086c")
//                        .build()
//        ));
//        locationRepository.saveAll(List.of(
//                Location.builder()
//                        .city("Oradea").build(),
//                Location.builder()
//                        .city("Cluj").build(),
//                Location.builder()
//                        .city("Timisoara").build(),
//                Location.builder()
//                        .city("Brasov").build(),
//                Location.builder()
//                        .city("Bucuresti").build()
//        ));
        //-------------------------------------------------------------------------
//        routeRepository.saveAll(List.of(
//                Route.builder()
//                        .start("Oradea")
//                        .destination("Cluj")
//                        .assignedTrainId("60a149a0e48c94212cde020f")
//                        .length(150).build(),
//                Route.builder()
//                        .start("Cluj")
//                        .destination("Timisoara")
//                        .assignedTrainId("60a149a0e48c94212cde0210")
//                        .length(260).build(),
//                Route.builder()
//                        .start("Timisoara")
//                        .destination("Brasov")
//                        .assignedTrainId("60a149a0e48c94212cde0211")
//                        .length(300).build()
//        ));
        //----------*****************------------------------------------------------------------
    }
}
