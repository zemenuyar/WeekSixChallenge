package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
@Controller
public class HomeController {

    @Autowired
    CourseRepository CourseRepository;
    @RequestMapping("/")
    public String listCourses(Model model){
        model.addAttribute("courses", CourseRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String processForm(Model model){
        model.addAttribute("course",new Course());
        return "courseform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Course course, BindingResult result){
        if (result.hasErrors()){
            return "courseform";
        }
        CourseRepository.save(course);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("course",CourseRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id")long id, Model model){
        model.addAttribute("course",CourseRepository.findById(id).get());
        return "courseform";

    }
    @RequestMapping("/delete/{id}")
    public String delcourse(@PathVariable("id")long id, Model model) {
        CourseRepository.deleteById(id);
        return "redirect:/";
    }

    }
