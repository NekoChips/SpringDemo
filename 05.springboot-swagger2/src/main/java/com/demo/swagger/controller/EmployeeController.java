package com.demo.swagger.controller;

import com.demo.swagger.bean.Employee;
import com.demo.swagger.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api("员工 Controller")
@RestController()
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation(value = "新增员工信息", notes = "根据员工实体创建员工对象")
    @ApiImplicitParam(name = "employee", value = "员工信息", required = true, dataType = "Employee")
    @PostMapping("add")
    public HttpEntity<?> add(@RequestBody Employee employee) {
        int count = employeeService.add(employee);
        return count > 0 ?
                ResponseEntity.ok("add employee success") :
                ResponseEntity.of(Optional.of("add employee fail"));
    }

    @ApiOperation(value = "删除员工信息", notes = "根据员工编号删除员工信息")
    @ApiImplicitParam(name = "emNo", value = "员工编号", required = true, dataTypeClass = String.class)
    @DeleteMapping("delete")
    public HttpEntity<?> delete(String emNo) {
        int count = employeeService.delete(emNo);
        return count > 0 ?
                ResponseEntity.ok("delete employee success") :
                ResponseEntity.of(Optional.of("delete employee fail"));
    }

    @ApiOperation(value = "更新员工信息", notes = "根据员工编号，更新员工信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "emNo", value = "员工编号", required = true, dataType = "String", paramType =
                    "path"),
            @ApiImplicitParam(name = "employee", value = "员工信息", required = true, dataType = "Employee")
    })
    @PutMapping("/{emNo}")
    public HttpEntity<?> update(@PathVariable("emNo") String emNo, @RequestBody() Employee employee) {
        employee.setEmNo(emNo);
        int count = employeeService.update(employee);
        return count > 0 ?
                ResponseEntity.ok("update employee success") :
                ResponseEntity.of(Optional.of("update employee fail"));
    }

    @ApiOperation(value = "查询员工信息", notes = "根据员工编号查询员工信息")
    @ApiImplicitParam(name = "emNo", value = "员工编号", required = true, dataType = "String")
    @GetMapping("info")
    public HttpEntity<?> queryById(String emNo) {
        Employee employee = employeeService.queryById(emNo);
        return ResponseEntity.ok(employee);
    }

    @ApiOperation(value = "查询员工列表", notes = "查询员工列表")
    @GetMapping("list")
    public HttpEntity<?> queryList() {
        List<Employee> employees = employeeService.queryList();
        return ResponseEntity.ok(employees);
    }
}
