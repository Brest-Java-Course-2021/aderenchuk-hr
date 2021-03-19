
package com.epam.brest.dao.jdbc;

import com.epam.brest.dao.DepartmentDao;
import com.epam.brest.model.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath*:dao.xml"})
    public class DepartmentDaoJdbcIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoJdbcIT.class);

        @Autowired
        private DepartmentDao departmentDao;

        @Test
        public void findAllTest() {
            List<Department> departments = departmentDao.findAll();
        Assertions.assertNotNull(departments);
        Assertions.assertTrue(departments.size() > 0);
    }

    @Test
    public void findByIdTest() {
        List<Department> departments = departmentDao.findAll();
        Assertions.assertNotNull(departments);
        Assertions.assertTrue(departments.size() > 0);

        Integer departmentId = departments.get(0).getDepartmentId();
        Department expDepartment = departmentDao.findById(departmentId).get();
        Assertions.assertEquals(departmentId, expDepartment.getDepartmentId());
        Assertions.assertEquals(departments.get(0).getDepartmentName(), expDepartment.getDepartmentName());
        Assertions.assertEquals(departments.get(0), expDepartment);

    }

    @Test
    public void findByIdExceptionalTest() {
            Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
                departmentDao.findById(999).get();
        });
    }

    @Test
    public void createDepartmentTest() {
        List<Department> departments = departmentDao.findAll();
        Assertions.assertNotNull(departments);
        Assertions.assertTrue(departments.size() > 0);

            departmentDao.create(new Department("DEV"));

        List<Department> realDepartments = departmentDao.findAll();
        Assertions.assertEquals(departments.size() + 1, realDepartments.size());
    }

    @Test
    public void createDepartmentWithTheSameNameTest() {
        List<Department> departments = departmentDao.findAll();
        Assertions.assertNotNull(departments);
        Assertions.assertTrue(departments.size() > 0);
        Assertions.assertThrows(IllegalArgumentException.class,  () -> {
            departmentDao.create(new Department("HR"));
            departmentDao.create(new Department("HR"));
        });
    }

    @Test
    public void createDepartmentWithTheSameNameDiffCaseTest() {
        List<Department> departments = departmentDao.findAll();
        Assertions.assertNotNull(departments);
        Assertions.assertTrue(departments.size() > 0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            departmentDao.create(new Department("HR"));
            departmentDao.create(new Department("Hr"));
        });
    }

    @Test
    public void updateDepartmentTest() {
        List<Department> departments = departmentDao.findAll();
        Assertions.assertNotNull(departments);
        Assertions.assertTrue(departments.size() > 0);

        Department department = departments.get(0);
        department.setDepartmentName("TEST_DEPARTMENT");

        departmentDao.update(department);

        Optional<Department> realDepartments = departmentDao.findById(department.getDepartmentId());
        Assertions.assertEquals("TEST_DEPARTMENT", realDepartments.get().getDepartmentName());
    }

//    @Test
//    public void updateDepartmentNotUniqueTest() {
//        List<Department> departments = departmentDao.findAll();
//        Assertions.assertNotNull(departments);
//        Assertions.assertTrue(departments.size() > 0);
//
//        Department department = departments.get(0);
//        department.setDepartmentName(departments.get(1).getDepartmentName());
//        departmentDao.update(department);
//    }
}