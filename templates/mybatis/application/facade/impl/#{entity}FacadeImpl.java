package #{group}.#{project}.feature.#{entityLowercase}.application.facade.impl;

import #{group}.#{project}.feature.#{entityLowercase}.application.exception.#{entity}NotFoundException;
import #{group}.#{project}.feature.#{entityLowercase}.application.facade.#{entity}Facade;
import #{group}.#{project}.feature.#{entityLowercase}.application.mapper.#{entity}Mapper;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.Create#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.request.Update#{entity}Request;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.Create#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.Get#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.application.model.response.Update#{entity}Response;
import #{group}.#{project}.feature.#{entityLowercase}.domain.model.#{entity};
import #{group}.#{project}.feature.#{entityLowercase}.domain.service.#{entity}Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class #{entity}FacadeImpl implements #{entity}Facade {
    private final #{entity}Service #{entityLowercase}Service;

    private final #{entity}Mapper #{entityLowercase}Mapper;

    @Override
    public Create#{entity}Response create(Create#{entity}Request request) {
        #{entity} #{entityLowercase} = #{entityLowercase}Mapper.toModel(request);
        #{entityLowercase}Service.add(#{entityLowercase});

        return #{entityLowercase}Mapper.toCreateResponse(#{entityLowercase});
    }

    @Override
    public Update#{entity}Response update(#{idType} id, Update#{entity}Request request) {
        #{entity} #{entityLowercase} = #{entityLowercase}Mapper.toModel(request);

        var existing#{entity} = #{entityLowercase}Service.get(id);

        if (existing#{entity}.isEmpty()) {
            throw new #{entity}NotFoundException();
        }

        var #{entityLowercase}Id = existing#{entity}.get().getId();

        #{entityLowercase}.setId(#{entityLowercase}Id);

        #{entityLowercase}Service.update(#{entityLowercase});

        return #{entityLowercase}Mapper.toUpdateResponse(#{entityLowercase});
    }

    @Override
    public List<Get#{entity}Response> getAll() {
        Collection<#{entity}> #{entityLowercase}s = #{entityLowercase}Service.getAll();

        return #{entityLowercase}s.stream()
                .map(#{entityLowercase}Mapper::toVehicleResponse)
                .toList();
    }

    @Override
    public Get#{entity}Response get(#{idType} id) {
        var #{entityLowercase} = #{entityLowercase}Service.get(id);

        if (#{entityLowercase}.isEmpty()) {
            throw new #{entity}NotFoundException();
        }

        return #{entityLowercase}Mapper.toVehicleResponse(#{entityLowercase}.get());
    }

    @Override
    public void delete(#{idType} id) {
        #{entityLowercase}Service.delete(id);
    }
}
