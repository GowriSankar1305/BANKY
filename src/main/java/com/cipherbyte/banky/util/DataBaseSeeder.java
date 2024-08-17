package com.cipherbyte.banky.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cipherbyte.banky.entity.Bank;
import com.cipherbyte.banky.entity.BankBranch;
import com.cipherbyte.banky.entity.District;
import com.cipherbyte.banky.entity.State;
import com.cipherbyte.banky.entity.SubDistrict;
import com.cipherbyte.banky.entity.Village;
import com.cipherbyte.banky.repository.BankBranchRepository;
import com.cipherbyte.banky.repository.BankRepository;
import com.cipherbyte.banky.repository.DistrictRepository;
import com.cipherbyte.banky.repository.StateRepository;
import com.cipherbyte.banky.repository.SubDistrictRepository;
import com.cipherbyte.banky.repository.VillageRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataBaseSeeder {

	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private SubDistrictRepository subDistrictRepository;
	@Autowired
	private VillageRepository villageRepository;
	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private BankBranchRepository bankBranchRepository;

	ObjectMapper mapper = new ObjectMapper();

	public String saveState(String stateName) {
		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			JsonNode parentNode = mapper.readValue(
					new File("C:\\Users\\0042R1744" + "\\Downloads\\states\\" + stateName + ".json"), JsonNode.class);
			State state = new State();
			state.setStateName(parentNode.findValue("state").asText());
			state = stateRepository.save(state);
			JsonNode rootNode = parentNode.findValues("districts").get(0);
			Iterator<JsonNode> districtItr = rootNode.elements();
			if (districtItr.hasNext()) {
				while (districtItr.hasNext()) {
					JsonNode districtNode = districtItr.next();
					District district = new District();
					district.setDistrictName(districtNode.findValue("district").asText());
					district.setState(state);
					district = districtRepository.save(district);

					JsonNode subDistricts = districtNode.findValues("subDistricts").get(0);
					Iterator<JsonNode> subDistrictItr = subDistricts.elements();
					if (subDistrictItr.hasNext()) {
						while (subDistrictItr.hasNext()) {
							JsonNode subDistrictNode = subDistrictItr.next();
							SubDistrict subDistrict = new SubDistrict();
							subDistrict.setSubDistrictName(subDistrictNode.findValue("subDistrict").asText());
							subDistrict.setDistrict(district);
							subDistrict = subDistrictRepository.save(subDistrict);

							JsonNode villages = subDistrictNode.findValues("villages").get(0);
							Iterator<JsonNode> villageItr = villages.elements();
							if (villageItr.hasNext()) {
								List<Village> villageList = new ArrayList<>();
								while (villageItr.hasNext()) {
									JsonNode villageNode = villageItr.next();
									Village village = new Village();
									village.setVillageName(villageNode.textValue());
									village.setSubDistrict(subDistrict);
									villageList.add(village);
								}
								villageRepository.saveAll(villageList);
							} else {
								log.info("------- villages are empty ------");
							}
						}
					} else {
						log.info("------ subdistricts are empty ------");
					}
					log.info("***********************************************************");
				}
			} else {
				log.info("----------- distrcits are empty ---------");
			}
		} catch (Exception e) {
			log.info(">>>>>>>>>>>>>>>>>>>>> Error while processing {}", e);
			return "{\"message\":\"" + e.getMessage() + "\"}";
		}
		log.info("############################ PROCESS FINISHED ########################################");
		return "{\"message\":\"State saved successfully\"}";
	}

	public String saveBank(String fileName) {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(
					new File("C:\\Users\\0042R1744\\Downloads\\banks\\" + fileName + ".xlsx"));
			Bank bank = new Bank();
			List<BankBranch> branchList = new ArrayList<>();
			for (int i = 0; i < workBook.getNumberOfSheets(); ++i) {
				XSSFSheet sheet = workBook.getSheetAt(i);
				for (int j = 1; j < sheet.getLastRowNum(); ++j) {
					Row row = sheet.getRow(j);
					Cell bankNameCell = row.getCell(0);
					Cell ifscCell = row.getCell(1);
					Cell branchNameCell = row.getCell(2);
					Cell addressCell = row.getCell(3);
					Cell city1Cell = row.getCell(4);
					Cell city2Cell = row.getCell(5);
					Cell stateCell = row.getCell(6);
					Cell stdCodeCell = row.getCell(7);
					Cell phoneCell = row.getCell(8);

					bank.setBankName(bankNameCell.getStringCellValue());

					BankBranch branch = new BankBranch();
					branch.setBranchAddress(addressCell.getStringCellValue());
					branch.setBranchName(branchNameCell.getStringCellValue());
					branch.setBranchPhone(Double.toString(phoneCell.getNumericCellValue()).split("\\.")[0]);
					branch.setCity1(city1Cell.getStringCellValue());
					branch.setCity2(city2Cell.getStringCellValue());
					branch.setIfsc(ifscCell.getStringCellValue());
					branch.setStdCode(Double.toString(stdCodeCell.getNumericCellValue()).split("\\.")[0]);
					branch.setStateName(stateCell.getStringCellValue());
					branchList.add(branch);
					// log.info("bank branch -------> {}", mapper.writeValueAsString(branch));
				}
			}

			bank = bankRepository.save(bank);
			for (BankBranch branch : branchList) {
				branch.setBank(bank);
			}
			bankBranchRepository.saveAll(branchList);
			log.info("########################## BANK SAVED ###################");
		} catch (Exception e) {
			log.info(">>>>>>>>>>>>>>>>>>>>> Error while processing {}", e);
			return "{\"message\":\"" + e.getMessage() + "\"}";
		}
		return "{\"message\":\"State saved successfully\"}";
	}
}
