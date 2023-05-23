package atm.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AppErrorController implements ErrorController {
    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        String prefix = "front";
        String path = String.valueOf(request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI));
        if (path != null && path.startsWith("/admin"))
            prefix = "admin";
        
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return prefix + "/error/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return prefix + "/error/500";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return prefix + "/error/403";
            }
        }

        return "error";
    }
}
