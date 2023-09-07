package com.departmentservice.department.controller;

import com.departmentservice.department.daprservice.DaprService;
import com.departmentservice.department.dto.DepartmentDTO;
import com.departmentservice.department.entity.Department;
import com.departmentservice.department.mapper.DepartmentMapper;
import com.departmentservice.department.service.DepartmentService;
import io.dapr.Topic;
import io.dapr.client.domain.CloudEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartment();
        List<DepartmentDTO> departmentDTOS = departments.stream().map(DepartmentMapper::departmentDTOMapper).toList();
        return new ResponseEntity<>(departmentDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable("id") Long id) {
        Optional<Department> department = departmentService.getDepartment(id);
        if (department.isEmpty()) {
            return new ResponseEntity<>("Department not exist with id : " + id, HttpStatus.NO_CONTENT);
        }
        DepartmentDTO departmentDTO = DepartmentMapper.departmentDTOMapper(department.get());
        return new ResponseEntity<DepartmentDTO>(departmentDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        Department departmentToAdd = DepartmentMapper.departmentMapper(departmentDTO);
        Department addedDepartment = departmentService.addDepartment(departmentToAdd);
        DepartmentDTO addedDepartmentDTO = DepartmentMapper.departmentDTOMapper(addedDepartment);
        return new ResponseEntity<>(addedDepartmentDTO, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody DepartmentDTO departmentDTO, @PathVariable("id") Long id) {
        Department departmentToUpdate = DepartmentMapper.departmentMapper(departmentDTO);
        Department updatedDepartment = departmentService.updateDepartment(departmentToUpdate, id);
        DepartmentDTO updatedDepartmentDTO = DepartmentMapper.departmentDTOMapper(updatedDepartment);
        return new ResponseEntity<>(updatedDepartmentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
