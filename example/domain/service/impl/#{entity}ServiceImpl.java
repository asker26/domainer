package #{group}.#{project}.feature.#{entityLowercase}.domain.service.impl;

import #{group}.#{project}.feature.#{entityLowercase}.domain.model.#{entity};
import #{group}.#{project}.feature.#{entityLowercase}.domain.repository.#{entity}Repository;
import #{group}.#{project}.feature.#{entityLowercase}.domain.service.#{entity}Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class #{entity}ServiceImpl implements #{entity}Service {
    final #{entity}Repository #{entityLowercase}Repository;

    @Override
    public void add(#{entity} #{entityLowercase}) {
        #{entityLowercase}Repository.add(#{entityLowercase});
    }

    @Override
    public void update(#{entity} #{entityLowercase}) {
        #{entityLowercase}Repository.update(#{entityLowercase});
    }

    @Override
    public void delete(#{idType} id) {
        #{entityLowercase}Repository.delete(id);
    }

    @Override
    public Optional<#{entity}> get(#{idType} id) {
        return #{entityLowercase}Repository.get(id);
    }

    @Override
    public Collection<#{entity}> getAll() {
        return #{entityLowercase}Repository.getAll();
    }
}

