package com.example.demo1.controllers;

import com.example.demo1.models.Student;
import com.example.demo1.services.HibernateStudentService;
import com.example.demo1.services.StudentServiceImp;
import com.example.demo1.services.StudentServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    List<Student> studentList = new ArrayList<>();
    @Autowired
    StudentServiceInterface studentService;
    HibernateStudentService hibernateStudentService = new HibernateStudentService();

    @GetMapping("/students")
    public ModelAndView getStudents(@RequestParam(value = "q",defaultValue="") String q,
                                    @RequestParam(value = "sort",defaultValue="id") String sort,
                                    @RequestParam(value = "dir",defaultValue="asc") String dir,
                                    @RequestParam(value = "limit",defaultValue = "10") int limit) {
        List<Student> students = new ArrayList<>();
        if(q != null || sort != null || dir != null || limit > 0) {
            students = hibernateStudentService.getStudents();
        }
        else {
            students = studentService.getStudents();
        }
        ModelAndView modelAndView = new ModelAndView("students");
        modelAndView.addObject("studentList", students);
        return modelAndView;
    }

    @GetMapping("/students/create-student")
    public String createStudent(Model model) {
        model.addAttribute("student", new Student());
        return "create-student";
    }

    @PostMapping("/students/create-student")
    public String createStudentSubmit(@Valid @ModelAttribute Student student,
                                      BindingResult binding,
                                      RedirectAttributes redirectAttributes
                                      ) {
        if(binding.hasErrors()) {
            return "create-student";
        }
        studentService.addStudent(student);
        redirectAttributes.addFlashAttribute("message", "Student created successfully!");
        return "redirect:/students";
    }

    @GetMapping("/students/{id}")
    public ModelAndView getStudent(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("student-details");
        Student student = studentService.getStudentById(id);
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @PostMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable("id") int id ,RedirectAttributes ra) {
        studentService.deleteStudent(id);
        ra.addFlashAttribute("message", "Đã xóa sinh viên!");
        return "redirect:/students";
    }

    @GetMapping("/students/{id}/edit")
    public ModelAndView editStudentGet(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("student-update");
        Student student = studentService.getStudentById(id);
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @PostMapping("/students/{id}/edit")
    public String editStudentPost(@PathVariable("id") int id,
                                  @ModelAttribute Student student) {
        studentService.updateStudent(student);
        return "redirect:/students";
    }
}
