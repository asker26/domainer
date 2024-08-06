package #{group}.#{project}.feature.#{entityLowercase}.infrastructure.persistence;

import #{group}.#{project}.feature.#{entityLowercase}.domain.repository.#{entity}Repository;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface #{entity}MyBatisRepository extends #{entity}Repository {
}