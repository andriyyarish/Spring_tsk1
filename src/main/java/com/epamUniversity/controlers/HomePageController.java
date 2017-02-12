package com.epamUniversity.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Andriy_Yarish on 1/7/2017.
 */
@Controller
public class HomePageController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexBasePath(){
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexFullPath(){
        return "index";
    }

}
