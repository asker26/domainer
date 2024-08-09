package #{group}.#{project}.feature.#{entityLowercase}.application.model.request.quest;

import lombok.Data;

@Data
public class Update#{entity}Request {
    private String name;

    private String description;

    private Long longitude;

    private Long latitude;
}
