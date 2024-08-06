package #{group}.#{project}.feature.#{entityLowercase}.domain.model;

import az.gov.dlp.domain.model.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class #{entity} extends BaseDomain<Long> {
}
