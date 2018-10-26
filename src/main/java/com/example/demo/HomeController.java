package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.resources.Messages;

import javax.validation.Valid;
@Controller
public class HomeController {

    @Autowired
    MessageRepository MessageRepository;
    @RequestMapping("/")
    public String listMessages(Model model){
        model.addAttribute("messages", MessageRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String processForm(Model model){
        model.addAttribute("message",new Message());
        return "Messageform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Message message, BindingResult result){
        if (result.hasErrors()){
            return "Messageform";
        }
        MessageRepository.save(message);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("message", MessageRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateMessage(@PathVariable("id")long id, Model model){
        model.addAttribute("message", MessageRepository.findById(id).get());
        return "Messageform";

    }
    @RequestMapping("/delete/{id}")
    public String delmessage(@PathVariable("id")long id, Model model) {
        MessageRepository.deleteById(id);
        return "redirect:/";
    }

    }
