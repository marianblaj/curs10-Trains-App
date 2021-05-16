package ro.fastrackit.trainsApp.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ro.fastrackit.trainsApp.model.CollectionResponse;
import ro.fastrackit.trainsApp.model.PageInfo;
import ro.fastrackit.trainsApp.model.entity.Route;
import ro.fastrackit.trainsApp.service.route.RouteService;

@RestController
@AllArgsConstructor
@RequestMapping("/routes")
public class RouteController {
    private final RouteService routeService;

    @GetMapping
    CollectionResponse<Route> getAll(Pageable pageable) {
        Page<Route> page = routeService.getAll(pageable);
        return CollectionResponse.<Route>builder()
                .content(page.getContent())
                .pageInfo(PageInfo.builder()
                        .totalPages(page.getTotalPages())
                        .totalElements(page.getNumberOfElements())
                        .crtPage(page.getNumber())
                        .pageSize(page.getSize())
                        .build())
                .build();
    }

    @PostMapping
    Route addRoute(@RequestBody Route newRoute) {
        return routeService.addRoute(newRoute);
    }
}
