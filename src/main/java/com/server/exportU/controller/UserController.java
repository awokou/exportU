package com.server.exportU.controller;

import com.lowagie.text.DocumentException;
import com.server.exportU.exports.UserPDFExport;
import com.server.exportU.repository.UserRepository;
import org.springframework.ui.Model;
import com.server.exportU.dto.UserDto;
import com.server.exportU.service.IUserService;
import com.server.exportU.imports.UserExcelImport;
import com.server.exportU.exports.UserExcelExport;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class UserController {

    private final IUserService userService;
    private final UserExcelImport userExcelImport;
    private final UserRepository userRepository;

    public UserController(IUserService userService, UserExcelImport userExcelImport, UserRepository userRepository) {
        this.userService = userService;
        this.userExcelImport = userExcelImport;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/import")
    public String createGetImport() {
        return "import";
    }

    @PostMapping("/import")
    public String createPostImport(@RequestParam(name = "file") MultipartFile file,Model model) throws Exception {
        // Check if database already has users
        if (userRepository.count() > 0) {
            model.addAttribute("message", "Users already exist in the database. Import skipped.");
            return "import"; // return to the import page with message
        }

        userExcelImport.importExcel(file);
        model.addAttribute("message", "Users imported successfully!");
        return "redirect:/index";
    }

    @GetMapping("/users/{id}")
    public String deleteUserById(@PathVariable(value = "id") Integer id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

    @GetMapping("/users/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<UserDto> listUsers = userService.getAllUsers();
        UserExcelExport excelExporter = new UserExcelExport(listUsers);
        excelExporter.export(response);
    }

    @GetMapping("/users/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<UserDto> listUsers = userService.getAllUsers();
        UserPDFExport exporter = new UserPDFExport(listUsers);
        exporter.export(response);

    }
}
