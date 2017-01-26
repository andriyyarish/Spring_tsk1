package epamUniversity.controlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import epamUniversity.model.Ticket;
import epamUniversity.model.User;
import epamUniversity.services.AccountService;
import epamUniversity.services.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.LinkedList;

import static epamUniversity.util.DatesHandling.parseStringToDate;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

/**
 * Created by Andriy_Yarish on 12/28/2016.
 */

@Controller
public class UsersController implements InitializingBean {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private GsonBuilder gsonBuilder;
    private User userProto;
    private Gson gson;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("userList", userService.getAll());
        return "usersFacade";
    }

    // the same path as for html representation but it works only with specific header type/value
    @RequestMapping(value = "/users", method = RequestMethod.GET, headers = "accept=application/pdf")
    public ModelAndView users() {
        ModelAndView model = new ModelAndView();
        model.setViewName("usersPdfView");
        model.addObject("usersList", userService.getAll());
        return model;
    }

    // this path is used in users view to demonstrate that PDF export works
    @RequestMapping(value = "/usersPdfView", method = RequestMethod.GET, headers = "accept=application/pdf")
    public ModelAndView usersToPdf() {
        ModelAndView model = new ModelAndView();
        model.setViewName("usersPdfView");
        model.addObject("usersList", userService.getAll());
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
    public String addUser_new(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String emai = request.getParameter("email");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (null != firstName && null != lastName && null != emai) {

            User user = new User(firstName, lastName, emai);
            DateTime date = parseStringToDate(dateOfBirth);
            user.setDateOfBirth(date);
            if (null != password && password.equals(confirmPassword)) {
                user.setPassword(password);
                userService.save(user);
            } else {
                throw new IllegalArgumentException("Please enter correct confirm password value");
            }
        } else {
            throw new IllegalArgumentException("Please fill all required fields");
        }
        return"redirect:/users.html";
}

    @RequestMapping(value = "/getUserByEmail")
    public ModelAndView getUserByEmail(HttpServletRequest request) {
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

    @RequestMapping(value = "/getUserById")
    public ModelAndView getUserById(HttpServletRequest request) {
        ModelAndView result = new ModelAndView();
        String id = request.getParameter("id");

        if (id != null) {
            User user = userService.getById(parseLong(id));
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

    @RequestMapping(value = "/users/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") long id,
                             @ModelAttribute("model") ModelMap model) {
        User u = userService.getById(id);
        if (u != null)
            userService.remove(u);
        //todo need to throw some exception
        model.addAttribute("userList", userService.getAll());
        return "redirect:/users.html";
    }

    @RequestMapping(value = "/users/{id}/getTickets", method = RequestMethod.GET)
    public ModelAndView getBookedTickets(@PathVariable(value = "id") long usrId, ModelAndView result) {
        User user = userService.getById(usrId);
        Collection<Ticket> tickets;
        Collection<String> ticketsView = new LinkedList<>();
        if (user != null) {
            tickets = user.getTickets();
            if (tickets != null && tickets.size() > 0)
                for (Ticket t : tickets)
                    ticketsView.add("User email: " + t.getUser().getEmail()
                            + " Ticket Id: " + t.getId());
        }
        result.addObject("tickets", ticketsView);
        result.setViewName("ticketsByUser");
        return result;
    }

    @RequestMapping(value = "users/refill/{id}")
    public String refillBallance(@PathVariable long id,@RequestParam String amount){
        User user = userService.getById(id);
        accountService.refillBalance(user,Double.parseDouble(amount));
        return "redirect:/users.html";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        gson = gsonBuilder.serializeNulls()
                .setPrettyPrinting()
                .disableInnerClassSerialization()
                .create();
    }

}
