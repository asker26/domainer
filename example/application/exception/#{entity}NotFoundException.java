package #{group}.#{project}.feature.#{entityLowercase}.application.exception;

import az.gov.dlp.exception.NotFoundException;
import az.gov.dlp.exception.model.ErrorDetails;

public class #{entity}NotFoundException extends NotFoundException {
    public #{entity}NotFoundException() {
        super(ErrorDetails.of("", "#{entity} not found",
                "",
                ErrorDetails.error(314_00, "#{entity}NotFound")));
    }
}
