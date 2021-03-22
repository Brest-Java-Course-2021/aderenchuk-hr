package com.epam.brest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentDtoService departmentDtoService;

    @Autowired
    public DepartmentController(DepartmentDtoService departmentDtoService) {
        this.departmentDtoService = departmentDtoService;
    }

    @GetMapping(value = "/departments")
    public String departments(Model model) {
        LOGGER.debug("departments()");
        model.addAttribute("departments", departmentDtoService.findAllWithAvgSalary());
        return "departments";
    }

    @GetMapping(value = "/department/{id}")
    public String gotoEditDepartmentPage(@PathVariable Integer id, Model model) {
        return "department";
    }

    @GetMapping(value = "/department/add")
    public String gotoAddDepartmentPage(Model model) {
        return "department";
    }

}
