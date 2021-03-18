package com.epam.brest.dao.jdbc;

import com.epam.brest.dao.DepartmentDao;
import com.epam.brest.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.ReflectionUtils;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DepartmentDaoJdbcMockTest {

    @InjectMocks
    private DepartmentDaoJdbc departmentDao;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Captor
    private ArgumentCaptor<RowMapper<Department>> captor;

    @Test
    public void findAllTest() {

        String sql = "select";
        ReflectionTestUtils.setField(departmentDao, "selectSql", sql);

        Department department = new Department();
        List<Department> list = Collections.singletonList(department);
        Mockito.when(namedParameterJdbcTemplate.query(any(), ArgumentMatchers.<RowMapper<Department>>any())).thenReturn(list);

        List<Department> result = departmentDao.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(result.get(0), department);

        Mockito.verify(namedParameterJdbcTemplate).query(eq(sql), captor.capture());

        RowMapper<Department> mapper = captor.getValue();
        Assertions.assertNotNull(mapper);

        Mockito.verifyNoMoreInteractions(namedParameterJdbcTemplate);
    }

    @Test
    public void createTest() {
//
//        Department department =  new Department();
//
//        Mockito.when(namedParameterJdbcTemplate.queryForObject(any(), any(SqlParameterSource.class), eq(Integer.class))).thenReturn(0);
//
//        departmentDao.create(department);
    }

}
