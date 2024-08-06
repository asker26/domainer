package #{group}.#{project}.feature.#{entityLowercase}.application.mapper;

import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.Create#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.Update#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.Create#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.Get#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.Update#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.domain.model.#{entity};
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface #{entity}Mapper {
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modified", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    #{entity} toModel(Create#{entity}Request request);

    @Mapping(target = "created", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modified", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    #{entity} toModel(Update#{entity}Request request);

    Create#{entity}Response toCreateResponse(#{entity} #{entityLowercase});

    Update#{entity}Response toUpdateResponse(#{entity} #{entityLowercase});

    Get#{entity}Response toVehicleResponse(#{entity} #{entityLowercase});
}