package ro.fastrackit.trainsApp.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fastrackit.trainsApp.model.entity.Location;
import ro.fastrackit.trainsApp.service.location.LocationService;

@RestController
@AllArgsConstructor
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    String getAll() {
        return locationService.getAll();
    }

    @PostMapping
    Location addLocation(@RequestBody Location newLocation) {
        return locationService.addLocation(newLocation);
    }
}
