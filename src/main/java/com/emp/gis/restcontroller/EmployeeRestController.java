package com.emp.gis.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emp.gis.appconstants.AppConstants;
import com.emp.gis.dto.EmployeeDto;
import com.emp.gis.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private final Logger log = LoggerFactory.getLogger(EmployeeRestController.class);

	
	@Autowired
	EmployeeService employeeService;
	

	@PostMapping("/save")
	public ResponseEntity<String> saveEmp(@RequestBody EmployeeDto employeeDto) {
		log.debug("REST request to save emp : {}", employeeDto);
		boolean saveEmp = employeeService.saveEmp(employeeDto);
		if (saveEmp) {
			return new ResponseEntity<>(AppConstants.SUCSAV, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(AppConstants.FAISAV, HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateEmp(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto) {
		log.debug("Rest request to save emp : {}", employeeDto);

		boolean update = employeeService.update(employeeDto.getEmpId(), employeeDto);
		return new ResponseEntity<String>(HttpStatus.OK);

	}

	@GetMapping("/getEmp")
	public ResponseEntity<EmployeeDto> getEmpById(@RequestParam Integer id) {
		log.debug("REST request to get emp ", id);
		EmployeeDto empById = employeeService.getEmpById(id);
		return new ResponseEntity<EmployeeDto>(empById, HttpStatus.OK);
	}

	@GetMapping("/getAllEmps")
	public ResponseEntity<List<EmployeeDto>> getAllEmps() {

		log.debug("Rest Request to getAll Employees ");
		List<EmployeeDto> allEmp = employeeService.getAllEmp();
		return new ResponseEntity<List<EmployeeDto>>(allEmp, HttpStatus.OK);
	}

	@DeleteMapping("/deleteEmp")
	public ResponseEntity<String> deleteByEmp(@RequestParam Integer id) {
		log.debug("REST request to delete by Emp", id);
		employeeService.deleteEmp(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
