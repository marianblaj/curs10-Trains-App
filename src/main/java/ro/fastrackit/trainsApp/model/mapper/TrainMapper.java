package ro.fastrackit.trainsApp.model.mapper;

import org.springframework.stereotype.Component;
import ro.fastrackit.trainsApp.model.dto.TrainDto;
import ro.fastrackit.trainsApp.model.entity.Train;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class TrainMapper implements Mapper<TrainDto, Train> {

    public TrainDto toDto(Train train) {
        if (train == null) {
            return null;
        }
        var target = new TrainDto();
        target.setModel(train.getModel());
        target.setCarts(train.getCarts());

        return target;
    }

    public Train toEntity(TrainDto trainDto) {

        if (trainDto == null) {
            return null;
        }
        var target = new Train();
        target.setModel(trainDto.getModel());
        target.setCarts(trainDto.getCarts());
        return target;
    }

    public List<TrainDto> toDtoList(List<Train> trainList) {
        return trainList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
