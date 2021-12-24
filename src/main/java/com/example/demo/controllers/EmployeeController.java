package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping("")
    public void create(@RequestBody Employee e){
        employeeService.create(e);
    }

    @GetMapping("{id}")
    public ResponseEntity<Mono<Employee>>findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping("/name/{name}")
    public Flux<Employee> findByName(@PathVariable("name") String name){
        return employeeService.findByName(name);
    }

    @GetMapping(value = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee>findAll(){
        return employeeService.findAll();
    }

    @PutMapping("")
    public Mono<Employee>update(@RequestBody Employee e){
        return employeeService.update(e);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id){
        employeeService.delete(id).subscribe();
    }


}
