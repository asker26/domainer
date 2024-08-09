package #{group}.#{project}.feature.#{entityLowercase}.domain.service.impl;

import #{group}.#{project}.feature.#{entityLowercase}.domain.model.#{entity};
import #{group}.#{project}.feature.#{entityLowercase}.domain.repository.#{entity}Repository;
import #{group}.#{project}.feature.#{entityLowercase}.domain.service.#{entity}Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class #{entity}ServiceImpl implements #{entity}Service {
    private final #{entity}Repository #{entityLowercase}Repository;

    @Override
    public #{entity} create(#{entity} #{entityLowercase}) {
        return #{entityLowercase}Repository.save(#{entityLowercase});
    }

    @Override
    public #{entity} update(#{entity} #{entityLowercase}) {
        return #{entityLowercase}Repository.save(#{entityLowercase});
    }

    @Override
    public Optional<#{entity}> get(#{idType} id) {
        return #{entityLowercase}Repository.findById(id);
    }

    @Override
    public List<#{entity}> getAll() {
        return #{entityLowercase}Repository.findAll();
    }

    @Override
    public void delete(#{idType} id) {
       #{entityLowercase}Repository.deleteById(id);
    }
}
