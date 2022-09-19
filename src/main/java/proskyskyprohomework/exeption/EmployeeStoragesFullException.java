package proskyskyprohomework.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeStoragesFullException extends RuntimeException{
    public EmployeeStoragesFullException() {
    }

    public EmployeeStoragesFullException( String message ) {
        super(message);
    }

    public EmployeeStoragesFullException( String message, Throwable cause ) {
        super(message, cause);
    }

    public EmployeeStoragesFullException( Throwable cause ) {
        super(cause);
    }

    public EmployeeStoragesFullException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
