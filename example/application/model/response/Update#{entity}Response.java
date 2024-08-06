package #{group}.#{project}.feature.#{entityLowercase}.application.model.response;

import lombok.Data;

@Data
public class Update#{entity}Response {
    private #{idType} id;
}
