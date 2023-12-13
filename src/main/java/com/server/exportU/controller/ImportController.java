package com.server.exportU.controller;

import com.server.exportU.model.User;
import com.server.exportU.repository.UserRepository;
import com.server.exportU.service.excel.ExcelGenerator;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@AllArgsConstructor
public class ImportController {

    private UserRepository userRepository;
    private ExcelGenerator excelGenerator;

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> excelUserReport() throws Exception {
        List<User> userList = userRepository.findAll();

        ByteArrayInputStream in = excelGenerator.exportExcel(userList);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=users.xlsx");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
}
