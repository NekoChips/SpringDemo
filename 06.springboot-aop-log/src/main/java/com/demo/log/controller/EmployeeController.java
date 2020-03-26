package com.demo.log.controller;

import com.demo.log.annotation.RecordLog;
import com.demo.log.bean.Employee;
import com.demo.log.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author NekoChips
 * @description 员工 Controller
 * @date 2020/3/26
 */
@RestController()
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping("add")
    @RecordLog("新增员工信息")
    public HttpEntity<?> add(@RequestBody Employee employee) {
        int count = employeeService.add(employee);
        return count > 0 ?
                ResponseEntity.ok("add employee success") :
                ResponseEntity.of(Optional.of("add employee fail"));
    }

    @DeleteMapping("delete")
    @RecordLog("删除员工信息")
    public HttpEntity<?> delete(String emNo) {
        int count = employeeService.delete(emNo);
        return count > 0 ?
                ResponseEntity.ok("delete employee success") :
                ResponseEntity.of(Optional.of("delete employee fail"));
    }

    @PutMapping("/{emNo}")
    @RecordLog("更新员工信息")
    public HttpEntity<?> update(@PathVariable("emNo") String emNo, @RequestBody() Employee employee) {
        employee.setEmNo(emNo);
        int count = employeeService.update(employee);
        return count > 0 ?
                ResponseEntity.ok("update employee success") :
                ResponseEntity.of(Optional.of("update employee fail"));
    }

    @GetMapping("info")
    @RecordLog("查询员工信息")
    public HttpEntity<?> queryById(String emNo) {
        Employee employee = employeeService.queryById(emNo);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("list")
    @RecordLog("查询员工列表")
    public HttpEntity<?> queryList() {
        List<Employee> employees = employeeService.queryList();
        return ResponseEntity.ok(employees);
    }
}
