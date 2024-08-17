package com.cipherbyte.banky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cipherbyte.banky.util.DataBaseSeeder;

@RestController
@SpringBootApplication
public class BankyApplication {

	@Autowired
	DataBaseSeeder seeder;
	
	public static void main(String[] args) {
		SpringApplication.run(BankyApplication.class, args);
	}
	
	@PostMapping("/banky/state/add")
	public ResponseEntity<?> addState(@RequestParam String state) throws Exception{
		System.out.println("======= in add state controller ====");
		return new ResponseEntity<>(seeder.saveState(state),HttpStatusCode.valueOf(200));
	}
	
	@PostMapping("/banky/bank/add")
	public ResponseEntity<?> addBank(@RequestParam String bank) throws Exception{
		System.out.println("======= in add bank controller ====");
		return new ResponseEntity<>(seeder.saveBank(bank),HttpStatusCode.valueOf(200));
	}
	
}
