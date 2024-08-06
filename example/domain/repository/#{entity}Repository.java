package #{group}.#{project}.feature.#{entityLowercase}.domain.repository;

import #{group}.#{project}.feature.#{entityLowercase}.domain.model.#{entity};
import az.gov.dlp.domain.CrudRepository;

public interface #{entity}Repository extends CrudRepository<#{entity}, Long> {
}