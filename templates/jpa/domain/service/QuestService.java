package #{group}.#{project}.feature.#{entityLowercase}.domain.service;

import #{group}.#{project}.feature.#{entityLowercase}.domain.model.#{entity};

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface #{entity}Service {
    #{entity} create(#{entity}#{entityLowercase});

    #{entity} update(#{entity}#{entityLowercase});

    Optional<#{entity}> get(UUID id);

    List<#{entity}> getAll();

    void delete(UUID id);
}
