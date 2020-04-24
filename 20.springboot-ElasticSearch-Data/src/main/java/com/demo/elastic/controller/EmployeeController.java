package com.demo.elastic.controller;

import com.demo.elastic.bean.Employee;
import com.demo.elastic.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author NekoChips
 * @description Employee Controller
 * @date 2020/3/31
 */
@RestController
@RequestMapping("/elastic/api")
public class EmployeeController
{

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("employee")
    public HttpEntity<?> save(@RequestBody Employee employee)
    {
        Employee result = employeeRepository.save(employee);
        return ResponseEntity.ok(result.getId());
    }

    @DeleteMapping("employee/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id)
    {
        employeeRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @PutMapping("employee")
    public HttpEntity<?> update(@RequestBody Employee employee)
    {
        boolean b = employeeRepository.existsById(employee.getId());
        if (b)
        {
            Employee result = employeeRepository.save(employee);
            return ResponseEntity.ok(result.getId());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("employee/{id}")
    public HttpEntity<?> queryInfo(@PathVariable Integer id)
    {
        Employee employee = employeeRepository.findById(id).orElse(new Employee());
        return ResponseEntity.ok(employee);
    }

    @GetMapping("employees")
    public HttpEntity<?> queryAll()
    {
        Page<Employee> employees = employeeRepository.findAll(PageRequest.of(0, 10));
        return ResponseEntity.ok(employees);
    }

    @GetMapping("employees/{lastName}")
    public HttpEntity<?> queryAllByLastName(@PathVariable String lastName)
    {
        List<Employee> employees = employeeRepository.findAllByLastName(lastName);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("employees/filter")
    public HttpEntity<?> queryAllByMany(@Param("lastName") String lastName, @Param("age") Integer age)
    {
        List<Employee> employees = employeeRepository.findEmployeesByLastNameAndAgeAfter(lastName, age);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("employees/search")
    public HttpEntity<?> queryAllByAbout(@Param("about") String about)
    {
        List<Employee> employees = employeeRepository.findEmployeesByAbout(about);
        return ResponseEntity.ok(employees);
    }
}
