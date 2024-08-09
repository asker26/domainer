package #{group}.#{project}.feature.#{entityLowercase}.application.mapper;

import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.#{entityLowercase}.Create#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.#{entityLowercase}.Update#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.#{entityLowercase}.Create#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.#{entityLowercase}.Get#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.#{entityLowercase}.Update#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.domain.model.#{entity};
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface #{entity}Mapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    #{entity} toModel(Create#{entity}Request request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    #{entity} toModel(Update#{entity}Request request);

    Create#{entity}Response toCreateResponse(#{entity}#{entityLowercase});

    Update#{entity}Response toUpdateResponse(#{entity}#{entityLowercase});

    Get#{entity}Response toGetResponse(#{entity}#{entityLowercase});
}
