package ro.fastrackit.trainsApp.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ro.fastrackit.trainsApp.model.CollectionResponse;
import ro.fastrackit.trainsApp.model.PageInfo;
import ro.fastrackit.trainsApp.model.TrainFilters;
import ro.fastrackit.trainsApp.model.dto.TrainDto;
import ro.fastrackit.trainsApp.model.entity.Train;
import ro.fastrackit.trainsApp.model.mapper.TrainMapper;
import ro.fastrackit.trainsApp.service.train.TrainService;

@RestController
@AllArgsConstructor
@RequestMapping("/trains")
public class TrainController {

    private final TrainService trainService;
    private final TrainMapper trainMapper;

    @GetMapping
    CollectionResponse<TrainDto> getAll(TrainFilters trainFilters) {
        Page<Train> page = trainService.getAll(trainFilters);

        return CollectionResponse.<TrainDto>builder()
                .content(trainMapper.toDtoList(page.getContent()))
                .pageInfo(PageInfo.builder()
                        .totalPages(page.getTotalPages())
                        .totalElements(page.getNumberOfElements())
                        .crtPage(page.getNumber())
                        .pageSize(page.getSize())
                        .build())
                .build();
    }

    @PostMapping
    Train addTrain(@RequestBody Train newTrain) {
        return trainService.addTrain(newTrain);
    }

    @PatchMapping("/{trainId}")
    Train patchTrain(@RequestBody JsonPatch patch, @PathVariable String trainId) {
        return trainService.patchTrain(patch, trainId);
    }

    @DeleteMapping("/{trainId}")
    void deleteTrain(@PathVariable String trainId) {
        trainService.deleteTrain(trainId);
    }
}
