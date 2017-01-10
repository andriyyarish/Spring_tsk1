package epamUniversity.controlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import epamUniversity.entities.Ticket;
import epamUniversity.services.UserService;
import epamUniversity.util.DatesHandling;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import epamUniversity.entities.User;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static epamUniversity.util.DatesHandling.parseStringToDate;
import static java.lang.Integer.parseInt;

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

    @RequestMapping (value = "/users", method = RequestMethod.GET)
    public String users(@ModelAttribute("model") ModelMap model){
        model.addAttribute("userList", userService.getAll());
        return "usersFacade";
    }
    // the same path as for html representation but it works only with specific header type/value
    @RequestMapping (value = "/users", method = RequestMethod.GET, headers = "accept=application/pdf")
    public ModelAndView users(){
        ModelAndView model = new ModelAndView();
        model.setViewName("usersPdfView");
        model.addObject("usersList",userService.getAll());
        return model;
    }

    // this path is used in users view to demonstrate that PDF export works
    @RequestMapping (value = "/usersPdfView", method = RequestMethod.GET, headers = "accept=application/pdf")
    public ModelAndView usersToPdf(){
        ModelAndView model = new ModelAndView();
        model.setViewName("usersPdfView");
        model.addObject("usersList",userService.getAll());
        return model;
    }

    // Alternative method was implemented because previous one was note to handle DateTime.
//    @RequestMapping (value = "/users", method = RequestMethod.POST)
//    public String addUser(@ModelAttribute("user") User user){
//        if(null != user && null != user.getFirstName() && null!=user.getLastName() && null != user.getEmail())
//            userService.save(user);
//        return "redirect:users.html";
//    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String addUser_new(HttpServletRequest request){
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String emai = request.getParameter("email");
        String dateOfBirth = request.getParameter("dateOfBirth");

        if(null != firstName && null!=lastName && null != emai){
            User user = new User(firstName,lastName,emai);
            DateTime date = parseStringToDate(dateOfBirth);
            user.setDateOfBirth(date);
            userService.save(user);
        }
        return "redirect:/users.html";
    }

    @RequestMapping (value = "/getUserByEmail")
    public ModelAndView getUserByEmail(HttpServletRequest request){
        ModelAndView result = new ModelAndView();
        String email = request.getParameter("email");

        if (email != null) {
            User user = userService.getUserByEmail(email);
            if (user != null) {
                result.addObject("user", user.getFirstName() + " " + user.getLastName());
            } else {
                result.addObject("user", "");
            }
            result.addObject("email", email);
        }
        result.setViewName("getUsrByEmail");
        return result;
    }

    @RequestMapping (value = "/getUserById")
    public ModelAndView getUserById(HttpServletRequest request){
        ModelAndView result = new ModelAndView();
        String id = request.getParameter("id");

        if (id != null) {
            User user = userService.getById(parseInt(id));
            if (user != null) {
                result.addObject("user", user.getFirstName() + " " + user.getLastName());
            } else {
                result.addObject("user", "");
            }
            result.addObject("id", id);
        }
        result.setViewName("getUsrById");
        return result;
    }

    @RequestMapping (value = "/users/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") int id,
                             @ModelAttribute("model") ModelMap model){
        User u = userService.getById(id);
        if(u!=null)
            userService.remove(u);
        //todo need to throw some exception
        model.addAttribute("userList", userService.getAll());
        return "redirect:/users.html";
    }

    @RequestMapping(value = "/users/{id}/getTickets", method = RequestMethod.GET)
    public ModelAndView getBookedTickets(@PathVariable(value = "id") int usrId, ModelAndView result){
        User user = userService.getById(usrId);
        Collection<Ticket> tickets;
        Collection<String> ticketsView = new LinkedList<>();
        if(user != null) {
            tickets = user.getTickets();
            if(tickets != null && tickets.size()>0)
                for (Ticket t: tickets)
                    ticketsView.add("Event: "+t.getEvent().getEventParent().getName()
                            +"Auditorium: " + t.getEvent().getAuditorium().getName());
        }
        result.addObject("tickets",ticketsView);
        result.setViewName("ticketsByUser");
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
