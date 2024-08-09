package #{group}.#{project}.feature.#{entityLowercase}.domain.service;

import #{group}.#{project}.feature.#{entityLowercase}.domain.model.#{entity};
import az.gov.dlp.domain.service.CrudService;

import java.util.Collection;

public interface #{entity}Service extends CrudService<#{entity}, #{idType}> {
}
