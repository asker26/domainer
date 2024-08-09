package #{group}.#{project}.feature.#{entityLowercase}.domain.service;

import #{group}.#{project}.feature.#{entityLowercase}.domain.model.#{entity};

import java.util.List;
import java.util.Optional;

public interface #{entity}Service {
    #{entity} create(#{entity}#{entityLowercase});

    #{entity} update(#{entity}#{entityLowercase});

    Optional<#{entity}> get(Long id);

    List<#{entity}> getAll();

    void delete(Long id);
}
