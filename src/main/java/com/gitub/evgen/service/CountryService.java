package com.gitub.evgen.service;

import com.gitub.evgen.model.Country;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CountryService implements ICountryService {

    private final ArrayList<Country> countries;

    public CountryService() {

        countries = new ArrayList();
    }

    @Override
    public ArrayList<Country> findAll() {

        FileInputStream fis = null;

        try {

            String fileName = "src/main/resources/countries.csv";

            fis = new FileInputStream(new File(fileName));
            CSVReader reader = new CSVReader(new InputStreamReader(fis));
            String[] nextLine;
            reader.readNext();

            while ((nextLine = reader.readNext()) != null) {

                Country newCountry = new Country(nextLine[0],
                        Integer.parseInt(nextLine[1]));
                countries.add(newCountry);
            }

        } catch (IOException | CsvValidationException ex) {
            Logger.getLogger(CountryService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(CountryService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return countries;
    }
}

