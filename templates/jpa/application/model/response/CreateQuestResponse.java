package #{group}.#{project}.feature.#{entityLowercase}.application.model.response.quest;

import lombok.Data;

import java.util.UUID;

@Data
public class Create#{entity}Response {
    private UUID id;

    private String name;

    private String description;

    private Long longitude;

    private Long latitude;
}
