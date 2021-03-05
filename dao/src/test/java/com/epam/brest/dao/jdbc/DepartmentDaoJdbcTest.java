
package com.epam.brest.dao.jdbc;

import com.epam.brest.dao.DepartmentDao;
import com.epam.brest.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
    public class DepartmentDaoJdbcTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoJdbcTest.class);

        @Autowired
        private DepartmentDao departmentDao;

        @Test
        public void findAllTest() {
            List<Department> departments = departmentDao.findAll();
        Assert.assertNotNull(departments);
        Assert.assertTrue(departments.size() > 0);
    }

    @Test
    public void findByIdTest() {
        List<Department> departments = departmentDao.findAll();
        Assert.assertNotNull(departments);
        Assert.assertTrue(departments.size() > 0);

        Integer departmentId = departments.get(0).getDepartmentId();
        Department expDepartment = departmentDao.findById(departmentId).get();
        Assert.assertEquals(departmentId, expDepartment.getDepartmentId());
        Assert.assertEquals(departments.get(0).getDepartmentName(), expDepartment.getDepartmentName());
        Assert.assertEquals(departments.get(0), expDepartment);

    }

    @Test (expected = EmptyResultDataAccessException.class)
    public void findByIdExceptionalTest() {
            departmentDao.findById(999).get();
    }

    @Test (expected = IllegalArgumentException.class)
    public void createDepartmentTest() {
        List<Department> departments = departmentDao.findAll();
        Assert.assertNotNull(departments);
        Assert.assertTrue(departments.size() > 0);

        departmentDao.create(new Department("HR"));

        List<Department> realDepartments = departmentDao.findAll();
        Assert.assertEquals(departments.size() + 1, realDepartments.size());
    }

    @Test (expected = IllegalArgumentException.class)
    public void createDepartmentWithTheSameNameTest() {
        List<Department> departments = departmentDao.findAll();
        Assert.assertNotNull(departments);
        Assert.assertTrue(departments.size() > 0);

        departmentDao.create(new Department("HR"));
        departmentDao.create(new Department("HR"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void createDepartmentWithTheSameNameDiffCaseTest() {
        List<Department> departments = departmentDao.findAll();
        Assert.assertNotNull(departments);
        Assert.assertTrue(departments.size() > 0);

        departmentDao.create(new Department("HR"));
        departmentDao.create(new Department("Hr"));
    }

    @Test
    public void updateDepartmentTest() {
        List<Department> departments = departmentDao.findAll();
        Assert.assertNotNull(departments);
        Assert.assertTrue(departments.size() > 0);

        Department department = departments.get(0);
        department.setDepartmentName("TEST_DEPARTMENT");

        departmentDao.update(department);

        Optional<Department> realDepartments = departmentDao.findById(department.getDepartmentId());
        Assert.assertEquals("TEST_DEPARTMENT", realDepartments.get().getDepartmentName());
    }

//    @Test
//    public void updateDepartmentNotUniqueTest() {
//        List<Department> departments = departmentDao.findAll();
//        Assert.assertNotNull(departments);
//        Assert.assertTrue(departments.size() > 0);
//
//        Department department = departments.get(0);
//        department.setDepartmentName(departments.get(1).getDepartmentName());
//        departmentDao.update(department);
//    }
}