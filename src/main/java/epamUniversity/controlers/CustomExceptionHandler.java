package epamUniversity.controlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;


@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler()
    public ModelAndView exceptionHandler(Exception ex){ //@ResponseBody
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exStackTrace = sw.toString();


        ModelAndView result = new ModelAndView();
        result.addObject("ex",ex);
        result.addObject("exStackTrace",exStackTrace);
        result.setViewName("error");
        return result;
    }

}