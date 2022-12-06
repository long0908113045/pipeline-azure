package com.example.pipelineazure.Employee;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
public class EmployeeController {


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("api/employees")
  String all() {
    return "Hello word";
  }
}
