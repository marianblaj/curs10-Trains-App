package ro.fastrackit.trainsApp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "Route")
public class Route {

    @Id
    String id;

    private int length;
    private String start;
    private String destination;

    private String assignedTrainId;
}
