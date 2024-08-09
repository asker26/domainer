package #{group}.#{project}.feature.#{entityLowercase}.domain.repository;

import #{group}.#{project}.feature.#{entityLowercase}.domain.model.#{entity};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface #{entity}Repository extends JpaRepository<#{entity}, Long> {
}
