package ro.fastrackit.trainsApp.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "Location")
public class Location {

    @Id
    String id;

    private String city;
}
