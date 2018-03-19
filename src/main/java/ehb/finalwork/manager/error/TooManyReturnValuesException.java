package ehb.finalwork.manager.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TooManyReturnValuesException extends Exception{

    public TooManyReturnValuesException(String message) {
        super(message);
    }
}
