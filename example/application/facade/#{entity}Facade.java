package #{group}.#{project}.feature.#{entityLowercase}.application.facade;

import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.Create#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.Update#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.Create#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.Get#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.Update#{entity}Response;

import java.util.List;

public interface #{entity}Facade {
    Create#{entity}Response create(Create#{entity}Request dto);

    Update#{entity}Response update(Long id, Update#{entity}Request dto);

    List<Get#{entity}Response> getAll();

    Get#{entity}Response get(Long id);

    void delete(Long id);
}
