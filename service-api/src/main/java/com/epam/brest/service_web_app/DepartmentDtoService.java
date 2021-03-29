package com.epam.brest.service_web_app;

import com.epam.brest.model.dto.DepartmentDto;

import java.util.List;

public interface DepartmentDtoService {

    List<DepartmentDto> findAllWithAvgSalary();
}
