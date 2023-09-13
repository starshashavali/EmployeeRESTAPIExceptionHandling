package com.emp.gis.service;

import java.util.List;

import com.emp.gis.dto.EmployeeDto;

public interface EmployeeService {

	public boolean saveEmp(EmployeeDto employeeDto);

	public boolean update(Integer id, EmployeeDto employeeDto);

	public EmployeeDto getEmpById(Integer id);

	public List<EmployeeDto> getAllEmp();

	public void deleteEmp(Integer id);

}
