package hu.bence.springapplications.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/gameoflife")
public class GameOfLifeController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView gameoflife(){
        return new ModelAndView("gameoflife");
    }
}
