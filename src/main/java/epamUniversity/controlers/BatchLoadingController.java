package epamUniversity.controlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import epamUniversity.model.User;
import epamUniversity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Andriy_Yarish on 1/8/2017.
 */
@Controller
@RequestMapping(value = "/batchLoad")
public class BatchLoadingController {
    private static final Logger logger = Logger.getLogger("BatchLoadingController");

    @Autowired
    private UserService userService;

    @RequestMapping()
    public ModelAndView batchLoad(){
        ModelAndView result = new ModelAndView("batchLoadForm");
        return result;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ModelAndView batchLoadUsers(@RequestParam MultipartFile file, ModelAndView result){

        try {
            byte[] fileBytes = file.getBytes();

            Gson gson = new GsonBuilder().create();
            Type type = new TypeToken<List<User>>(){}.getType();
            Reader reader = new InputStreamReader(file.getInputStream());
            List<User> users = gson.fromJson(reader,type);

            for (User u: users ) {
                userService.save(u);
            }

            result.addObject("users", users);

        }catch (IOException io){
            result.setViewName("error");
            result.addObject("ex",io);
            return result;
        }

        result.setViewName("batchUsers");
        result.addObject("test", "TEST");

        return result;
    }




}
