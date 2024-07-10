package com.accesodatos.database;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.accesodatos.entity.Bank;
import com.accesodatos.entity.City;
import com.accesodatos.entity.Customer;
import com.accesodatos.service.BankService;
import com.accesodatos.service.CityService;
import com.accesodatos.service.CustomerService;

/**
 * This class is used to put all the data we want every time 
 * we load the API into the database.
 */

@Component
public class InitDatabase implements CommandLineRunner{

	@Autowired
	CityService cityService;
	
	@Autowired
	BankService bankService;
	
	@Autowired
	CustomerService customerService;
	@Override
	public void run(String... args) throws Exception {
		// Create customers:
		
		Customer customerA = new Customer("Luis", "González", "Luis@gmail.com", "111111111");
		Customer customerB = new Customer("Miguel", "Rodríguez", "Miguel@gmail.com", "222222222");
		Customer customerC = new Customer("Antonio", "García", "Antonio@gmail.com", "333333333");
		Customer customerD = new Customer("Marcos", "Alonso", "Marcos@gmail.com", "444444444");
		Customer customerE = new Customer("Alberto", "Martínez", "Alberto@gmail.com","555555555");
		Customer customerF = new Customer("Naira", "García", "Naira@gmail.com", "666666666");
		Customer customerG = new Customer("Andrea", "García", "Andrea@gmail.com", "77777777");
		Customer customerH = new Customer("Eduardo", "Pérez", "Eduardo@gmail.com", "88888888");
		
		// Create banks
		
		Bank bankA = new Bank("BBVA", "C/ La Herradura, 14");
		Bank bankB = new Bank("Santander", "C/ María Aux, 24");
		Bank bankC = new Bank("La Caixa", "C/ Pedregal, 44");
		Bank bankD = new Bank("Bank of America", "Pasaje Caracas, 64");
		
		// Create cities
		
		City cityA = new City("Santa Cruz");
		City cityB = new City("La Laguna");
		City cityC = new City("Los Cristianos");
		City cityD = new City("Güimar");
		
		// Added cities
		
		cityService.saveCityApplication(cityA);
		cityService.saveCityApplication(cityB);
		cityService.saveCityApplication(cityC);
		cityService.saveCityApplication(cityD);
		
		// Added customers and banks
		
		customerA.addBank(bankA);
		customerA.addBank(bankC);
		customerA.setCity(cityB);
		
		customerB.addBank(bankA);
		customerB.addBank(bankC);
		customerB.addBank(bankD);
		customerB.setCity(cityA);
		
		customerC.addBank(bankA);
		customerC.addBank(bankC);
		customerC.setCity(cityA);
		
		customerD.addBank(bankA);
		customerD.setCity(cityC);
		
		customerE.addBank(bankB);
		customerE.setCity(cityB);
		
		customerF.addBank(bankB);
		customerF.setCity(cityA);
		
		customerG.addBank(bankB);
		customerG.setCity(cityC);
		
		customerH.addBank(bankB);
		customerH.setCity(cityA);
		
		customerService.saveCityCustomerList(Arrays.asList(
				customerA, customerB, customerC, customerD,
				customerE, customerF, customerG, customerH
				)); 
		
		
	}

}
