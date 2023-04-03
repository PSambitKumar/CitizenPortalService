package com.sambit.citizenportalservice.controller;

import com.sambit.citizenportalservice.model.Country;
import com.sambit.citizenportalservice.repository.CountryRepository;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 07/01/2023 - 9:03 PM
 */
@Controller
@RequestMapping("/api/migration")
public class DataMigrationController {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("file-location");
    private final CountryRepository countryRepository;

    public DataMigrationController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

//    Add Country List Data To Database From Excel Sheet
    @GetMapping(value = "/addCountryData")
    public String addCountryData(){
        System.out.println("Inside Add Country Data Method.");
        String xlsxFilePath = resourceBundle.getString("countryList.xlsx");
        Country country = null;
        List<Country> countryList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(xlsxFilePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            System.out.println("Sheet Name : "+sheet.getSheetName());
            int rowCount = sheet.getLastRowNum();
            int columnCount = sheet.getRow(0).getLastCellNum();
            System.out.println("Row Count : "+rowCount + ", Column Count : "+columnCount);
            for (int i = 3; i <= rowCount; i++) {
                country = new Country();
                for (int j = 0; j < columnCount; j++) {
                    if (j == 1)
                        country.setCountryName(String.valueOf(sheet.getRow(i).getCell(j)));
                    else if (j == 2)
                        country.setPopulation(String.valueOf(sheet.getRow(i).getCell(j)));
                    else if (j == 4)
                        country.setPopulationPercentage(String.valueOf(Double.parseDouble(String.valueOf(sheet.getRow(i).getCell(j))) * 100));
                }
                countryList.add(country);
            }
            countryList.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Success";
    }

//    Add Country List Data To Database From Excel Sheet 1
    @GetMapping(value = "/addCountryData1")
    public String addCountryData1(){
        System.out.println("Inside Add Country Data 1 Method.");
        String xlsxFilePath = resourceBundle.getString("countryList1.xlsx");
        Country country;
        List<Country> countryList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(xlsxFilePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            System.out.println("Sheet Name : "+sheet.getSheetName());
            int rowCount = sheet.getLastRowNum();
            int columnCount = sheet.getRow(0).getLastCellNum();
            System.out.println("Row Count : "+rowCount + ", Column Count : "+columnCount);
            for (int i = 1; i <= rowCount; i++) {
                country = new Country();
                for (int j = 0; j < columnCount; j++) {
                    if (j == 0)
                        country.setCountryCode(String.valueOf(sheet.getRow(i).getCell(j)));
                    else if (j == 1)
                        country.setCountryName(String.valueOf(sheet.getRow(i).getCell(j)));
                    else if (j == 4)
                        country.setCurrencyName(String.valueOf(sheet.getRow(i).getCell(j)));
                    else if (j == 5)
                        country.setCurrencyCode(String.valueOf(sheet.getRow(i).getCell(j)));
                    country.setActive(true);
                }
                countryList.add(country);
            }
//            countryList.forEach(System.out::println);
            countryRepository.saveAllAndFlush(countryList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Success";
    }


}
