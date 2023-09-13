package com.emp.gis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emp.gis.domain.Employee;
import com.emp.gis.dto.EmployeeDto;
import com.emp.gis.exception.EmployeeNotFoundException;
import com.emp.gis.repo.EmployeeRepo;
import com.emp.gis.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	public boolean saveEmp(EmployeeDto employeeDto) {
		log.debug("request to save employeeDto ", employeeDto);
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDto, employee);
		employeeRepo.save(employee);
		return true;
	}

	@Override
	public boolean update(Integer id, EmployeeDto employeeDto) {

		log.debug("request to update ", employeeDto);
		Optional<Employee> findById = employeeRepo.findById(id);
		if (findById.isPresent()) {
			Employee employee = findById.get();
			employeeRepo.save(employee);
			return true;
		}
		return false;
	}

	@Override
	public EmployeeDto getEmpById(Integer id) {
		log.debug("request to get Emp ", id);
		Optional<Employee> findById = employeeRepo.findById(id);
		if (findById.isPresent()) {
			Employee employee = findById.get();
			EmployeeDto dto = new EmployeeDto();
			BeanUtils.copyProperties(employee, dto);
			return dto;
		} else {
			throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
		}
	}

	@Override
	public List<EmployeeDto> getAllEmp() {
		log.debug("request to listOfEmps ");
		List<Employee> findAll = employeeRepo.findAll();
		List<EmployeeDto> employeeDto = new ArrayList<>();
		for (Employee emp : findAll) {
			EmployeeDto dto = new EmployeeDto();
			BeanUtils.copyProperties(emp, dto);
			employeeDto.add(dto);
		}
		return employeeDto;
	}

	@Override
	public void deleteEmp(Integer id) {
		log.debug("request to deleteByEmp ", id);
		Optional<Employee> findById = employeeRepo.findById(id);
		if (findById.isPresent()) {
			employeeRepo.deleteById(id);
		} else {
			throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
		}
	}
}
