package com.noel.parser.csv.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileService {
    public void processFile(MultipartFile file){
        try {
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            Reader reader = new InputStreamReader(file.getInputStream());
//            CSVReader csvReader = new CSVReader(reader);
            CSVReader csvReader = new CSVReaderBuilder(reader)
//                    .withSkipLines(1)
                    .build();
            String[] headers = csvReader.readNext();
            Map<String,Integer> headerMap = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                headerMap.put(headers[i],i);
            }
            String[] nextRecord;
            while ((nextRecord=csvReader.readNext()) != null){
                /*for (String cell:nextRecord){
                    System.out.print(cell+"\t");
                }*/
                String cellValue = nextRecord[headerMap.get("header_value")];
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
