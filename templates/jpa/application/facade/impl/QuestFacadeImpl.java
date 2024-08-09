package #{group}.#{project}.feature.#{entityLowercase}.application.facade.impl;

import #{group}.#{project}.exception.ResourceNotFoundException;
import #{group}.#{project}.feature.#{entityLowercase}.application.facade.#{entity}Facade;
import #{group}.#{project}.feature.#{entityLowercase}.application.mapper.#{entity}Mapper;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.#{entityLowercase}.Create#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.#{entityLowercase}.Update#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.#{entityLowercase}.Create#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.#{entityLowercase}.Get#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.#{entityLowercase}.Update#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.domain.service.#{entity}Service;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class #{entity}FacadeImpl implements #{entity}Facade {
    #{entity}Service#{entityLowercase}Service;
    #{entity}Mapper#{entityLowercase}Mapper;

    @Override
    public Create#{entity}Response create(Create#{entity}Request request) {
        var #{entityLowercase}Model = #{entityLowercase}Mapper.toModel(request);
        var #{entityLowercase} = #{entityLowercase}Service.create(#{entityLowercase}Model);

        return #{entityLowercase}Mapper.toCreateResponse(quest);
    }

    @Override
    public Update#{entity}Response update(UUID id, Update#{entity}Request request) {
        var #{entityLowercase}Model = #{entityLowercase}Mapper.toModel(request);

        var existing#{entity} = #{entityLowercase}Service.get(id);

        if (existing#{entity}.isEmpty()) {
            throw new ResourceNotFoundException("#{entity} with id " + id + " does not exist");
        }

       #{entityLowercase}Model.setId(id);

        var #{entityLowercase} = #{entityLowercase}Service.create(questModel);

        return #{entityLowercase}Mapper.toUpdateResponse(quest);
    }

    @Override
    public Get#{entity}Response get(UUID id) {
        var existing#{entity} = #{entityLowercase}Service.get(id);

        if (existing#{entity}.isEmpty()) {
            throw new ResourceNotFoundException("#{entity} with id " + id + " does not exist");
        }

        var #{entityLowercase} = existing#{entity}.get();

        return #{entityLowercase}Mapper.toGetResponse(quest);
    }

    @Override
    public List<Get#{entity}Response> getAll() {
        var result = #{entityLowercase}Service.getAll();

        return result.stream()
                .map(questMapper::toGetResponse)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        var existing#{entity} = #{entityLowercase}Service.get(id);

        if (existing#{entity}.isEmpty()) {
            throw new ResourceNotFoundException("#{entity} with id " + id + " does not exist");
        }

       #{entityLowercase}Service.delete(id);
    }
}
