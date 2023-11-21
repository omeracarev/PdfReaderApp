package org.newest.testwarapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {

    private static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

    @Autowired
    private PdfService pdfService;

    File directory = new File(uploadDirectory);
    Boolean isExist = directory.exists();

    @GetMapping("/upload-form")
    public String form() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestPart("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Lütfen bir dosya seçin.");
            return "upload";
        }
        try {

            if (!isExist){
                directory.mkdir();
            }

            byte[] bytes = file.getBytes();
            /*
             Path path = Paths.get(uploadDirectory + "/" + file.getOriginalFilename());
            Files.write(path, bytes);
             */

            Pdfs pdf = pdfService.savePdf(new Pdfs(file.getOriginalFilename(), bytes));

            model.addAttribute("message", "Dosya başarıyla yüklendi: " + file.getOriginalFilename());
            return "redirect:/index";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }
}
