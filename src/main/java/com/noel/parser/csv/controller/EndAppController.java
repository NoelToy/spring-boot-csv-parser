package com.noel.parser.csv.controller;

import com.noel.parser.csv.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/endApp")
public class EndAppController {
    @Autowired
    private FileService fileService;
    @RequestMapping(path = "/uploadCSV",method = RequestMethod.POST,consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Boolean> uploadCSV(@RequestParam("file")MultipartFile file){
        try {
            fileService.processFile(file);
            return ResponseEntity.ok(true);
        }
        catch (Exception ex){
            return ResponseEntity.internalServerError().header("ERROR", ex.getMessage()).build();
        }
    }
}
