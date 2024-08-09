package #{group}.#{project}.feature.#{entityLowercase}.application.facade.impl;

import #{group}.#{project}.exception.ResourceNotFoundException;
import #{group}.#{project}.feature.#{entityLowercase}.application.facade.#{entity}Facade;
import #{group}.#{project}.feature.#{entityLowercase}.application.mapper.#{entity}Mapper;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.Create#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.Update#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.Create#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.Get#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.Update#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.domain.service.#{entity}Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class #{entity}FacadeImpl implements #{entity}Facade {
    private final #{entity}Service #{entityLowercase}Service;
    private final #{entity}Mapper #{entityLowercase}Mapper;

    @Override
    public Create#{entity}Response create(Create#{entity}Request request) {
        var #{entityLowercase}Model = #{entityLowercase}Mapper.toModel(request);
        var #{entityLowercase} = #{entityLowercase}Service.create(#{entityLowercase}Model);

        return #{entityLowercase}Mapper.toCreateResponse(#{entityLowercase});
    }

    @Override
    public Update#{entity}Response update(#{idType} id, Update#{entity}Request request) {
        var #{entityLowercase}Model = #{entityLowercase}Mapper.toModel(request);

        var existing#{entity} = #{entityLowercase}Service.get(id);

        if (existing#{entity}.isEmpty()) {
            throw new ResourceNotFoundException("#{entity} with id " + id + " does not exist");
        }

       #{entityLowercase}Model.setId(id);

        var #{entityLowercase} = #{entityLowercase}Service.create(#{entityLowercase}Model);

        return #{entityLowercase}Mapper.toUpdateResponse(#{entityLowercase});
    }

    @Override
    public Get#{entity}Response get(#{idType} id) {
        var existing#{entity} = #{entityLowercase}Service.get(id);

        if (existing#{entity}.isEmpty()) {
            throw new ResourceNotFoundException("#{entity} with id " + id + " does not exist");
        }

        var #{entityLowercase} = existing#{entity}.get();

        return #{entityLowercase}Mapper.toGetResponse(#{entityLowercase});
    }

    @Override
    public List<Get#{entity}Response> getAll() {
        var result = #{entityLowercase}Service.getAll();

        return result.stream()
                .map(#{entityLowercase}Mapper::toGetResponse)
                .toList();
    }

    @Override
    public void delete(#{idType} id) {
        var existing#{entity} = #{entityLowercase}Service.get(id);

        if (existing#{entity}.isEmpty()) {
            throw new ResourceNotFoundException("#{entity} with id " + id + " does not exist");
        }

       #{entityLowercase}Service.delete(id);
    }
}
