package com.epam.brest.service_web_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EmployeeController {

    @GetMapping(value = "/employees")
    public final String employees(Model model) {
        return "employees";
    }

    @GetMapping(value = "/employee/{id}")
    public final String gotoEditEmployeePage(@PathVariable Integer id, Model model) {
        return "employee";
    }

    @GetMapping(value = "/employee/add")
    public final String gotoAddEmployeePage(Model model) {
        return "employee";
    }
}
