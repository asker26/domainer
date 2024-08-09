package #{group}.#{project}.feature.#{entityLowercase}.application.facade;

import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.#{entityLowercase}.Create#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.#{entityLowercase}.Update#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.#{entityLowercase}.Create#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.#{entityLowercase}.Get#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.#{entityLowercase}.Update#{entity}Response;

import java.util.List;
import java.util.UUID;

public interface #{entity}Facade {
    Create#{entity}Response create(Create#{entity}Request request);

    Update#{entity}Response update(UUID id, Update#{entity}Request request);

    Get#{entity}Response get(UUID id);

    List<Get#{entity}Response> getAll();

    void delete(UUID id);
}
