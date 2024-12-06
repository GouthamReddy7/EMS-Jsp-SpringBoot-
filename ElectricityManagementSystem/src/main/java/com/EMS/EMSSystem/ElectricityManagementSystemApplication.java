package com.EMS.EMSSystem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.EMS.EMSSystem.Util.DButil;

@SpringBootApplication
public class ElectricityManagementSystemApplication {

	public static void main(String[] args) {
	    DButil.initializeDatabase();

		SpringApplication.run(ElectricityManagementSystemApplication.class, args);
	}

}