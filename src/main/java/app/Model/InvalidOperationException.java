package app.Model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Specified Operation Not Allowed")
public class InvalidOperationException extends RuntimeException {
}