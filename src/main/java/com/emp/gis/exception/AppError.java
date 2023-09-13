package com.emp.gis.exception;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AppError {
	
	private Integer errorCode;
	
	private String  errorDes;
	
	private Date  date;

}
