package app.Model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException() {
        super("Specified Operation Not Allowed");
    }

    public InvalidOperationException(String message) {
        super(message);
    }
}