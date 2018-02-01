package app.Model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Flora2 could not start due to Syntax errors.")
public class FatalFlora2Error extends RuntimeException {

    public FatalFlora2Error() {
    }

    public FatalFlora2Error(Throwable cause) {
        super(cause);
    }
}