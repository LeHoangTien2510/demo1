package com.example.demo1.controllers;

import com.example.demo1.models.Student;
import com.example.demo1.services.StudentServiceImp;
import com.example.demo1.services.StudentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    List<Student> studentList = new ArrayList<>();
    @Autowired
    StudentServiceInterface studentService;

    @GetMapping("/students")
    public ModelAndView getStudents(@RequestParam(value = "q",defaultValue="") String q,
                                    @RequestParam(value = "sort",defaultValue="id") String sort,
                                    @RequestParam(value = "dir",defaultValue="asc") String dir,
                                    @RequestParam(value = "limit",defaultValue = "10") int limit) {
        List<Student> students = new ArrayList<>();
        if(q != null || sort != null || dir != null || limit > 0) {
            students = studentService.getStudents(q,sort,dir,limit);
        }
        else {
            students = studentService.getStudents();
        }
        ModelAndView modelAndView = new ModelAndView("students");
        modelAndView.addObject("studentList", students);
        return modelAndView;
    }

    @GetMapping("/students/create-student")
    public String createStudent() {
        return "create-student";
    }

    @PostMapping("/students/create-student")
    public String createStudentSubmit(@RequestParam("name") String name,
                                      @RequestParam("final_score") double finalScore, RedirectAttributes redirectAttributes) {
        Student student = new Student(name, finalScore);
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
        ModelAndView modelAndView = new ModelAndView("update-student");
        Student student = studentService.getStudentById(id);
        modelAndView.addObject("s", student);
        return modelAndView;
    }

    @PostMapping("/students/{id}/edit")
    public String editStudentPost(@PathVariable("id") int id,
                                  @RequestParam("name") String name,
                                  @RequestParam("final_score") double final_score) {
        Student student = new Student(id,name, final_score);
        studentService.updateStudent(student);
        return "redirect:/students";
    }
}
