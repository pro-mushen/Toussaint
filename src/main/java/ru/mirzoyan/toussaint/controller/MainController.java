package ru.mirzoyan.toussaint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirzoyan.toussaint.domain.User;
import ru.mirzoyan.toussaint.repo.MessageRepo;

import java.util.Arrays;
import java.util.HashMap;


@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    ApplicationContext applicationContext;


    @GetMapping("/")
    public String main(
            Model model,
            @AuthenticationPrincipal User user

    ){
        HashMap<Object,Object> data = new HashMap<>();
        data.put("profile", user);
        data.put("messages", messageRepo.findAll());

        model.addAttribute("frontendData", data);
       /* Arrays.asList(applicationContext.getBeanDefinitionNames()).stream().forEach(b-> System.out.println(b));*/
        return "index";
    }

}
