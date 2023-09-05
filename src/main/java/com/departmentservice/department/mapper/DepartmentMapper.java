package com.departmentservice.department.mapper;

import com.departmentservice.department.dto.DepartmentDTO;
import com.departmentservice.department.entity.Department;

public class DepartmentMapper {

    private DepartmentMapper() {
    }

    public static Department departmentMapper(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setDepartmentId(departmentDTO.getDepartmentId());
        department.setDepartmentName(departmentDTO.getDepartmentName());
        //department.setEmployeeList(departmentDTO.getEmployees());
        return department;
    }

    public static DepartmentDTO departmentDTOMapper(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentId(department.getDepartmentId());
        departmentDTO.setDepartmentName(department.getDepartmentName());
        //departmentDTO.setEmployees(department.getEmployeeList());
        return departmentDTO;
    }
}
