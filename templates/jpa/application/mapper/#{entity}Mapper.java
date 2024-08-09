package #{group}.#{project}.feature.#{entityLowercase}.application.mapper;

import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.Create#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.Update#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.Create#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.Get#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.Update#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.domain.model.#{entity};
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface #{entity}Mapper {
    #{entity} toModel(Create#{entity}Request request);

    #{entity} toModel(Update#{entity}Request request);

    Create#{entity}Response toCreateResponse(#{entity} #{entityLowercase});

    Update#{entity}Response toUpdateResponse(#{entity} #{entityLowercase});

    Get#{entity}Response toGetResponse(#{entity} #{entityLowercase});
}
