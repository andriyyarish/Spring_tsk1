package epamUniversity.controlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import epamUniversity.services.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import epamUniversity.entities.User;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andriy_Yarish on 12/28/2016.
 */

@Controller
public class UsersController implements InitializingBean {
    @Autowired
    private UserService userService;
    @Autowired
    private GsonBuilder gsonBuilder;
    private User userProto;
    private Gson gson;

    @RequestMapping (value = "/index", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping (value = "/users", method = RequestMethod.GET)
    public String users(@ModelAttribute("model") ModelMap model){
        model.addAttribute("userList", userService.getAll());
        return "users";
    }

    @RequestMapping (value = "/users", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user){
        if(null != user && null != user.getFirstName() && null!=user.getLastName() && null != user.getEmail())
            userService.save(user);
        return "redirect:users.html";
    }

    @RequestMapping (value = "/users/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") int id,
                             @ModelAttribute("model") ModelMap model){
        User u = userService.getById(id);
        if(u!=null)
            userService.remove(u);
        //todo need to throw some exception
        model.addAttribute("userList", userService.getAll());
        return "users";
    }

    @RequestMapping (value = "/getUserByEmail")
    public ModelAndView searchUser(HttpServletRequest request){
        ModelAndView result = new ModelAndView();
        String email = request.getParameter("email");

        if (email != null) {
            User user = userService.getUserByEmail(email);
            if (user != null) {
                result.addObject("user", user.getFirstName() + "" + user.getLastName());
            } else {
                result.addObject("user", "");
            }
            result.addObject("email", email);
        }
        result.setViewName("getUsrByEmail");
        return result;
    }



//    @RequestMapping (value = "/users", method = RequestMethod.GET)
//    public String getAllUsers(ModelMap modelMap){
//        Map<Integer, User> userList = userService.getUserList();
//
//        for(int i = 0; i<userList.size();i++)
//            modelMap.addAttribute("usr"+i,userList.get(i) );
//
//        modelMap.addAttribute("listSize",userList.size());
//        return "users";
//    }
//
//    @RequestMapping (value = "/user/{id}", method = RequestMethod.GET, produces =MediaType.APPLICATION_JSON_VALUE)
//    public ModelAndView getUserById(@PathVariable(value = "id") int id){
//        User userById = userService.getUserById(id);
//        return new ModelAndView("users", "message", userById.toString());
//    }
//
//    @RequestMapping (value = "/users", method = RequestMethod.POST)
//    public String registerUser(@ModelAttribute("test") User user, ModelMap modelMap){
//        modelMap.addAttribute("name",user.getName());
//        modelMap.addAttribute("email",user.getEmail());
//        return "result";
//    }




    @Override
    public void afterPropertiesSet() throws Exception {
        gson = gsonBuilder.serializeNulls()
                .setPrettyPrinting()
                .disableInnerClassSerialization()
                .create();
    }
}
