package org.newest.testwarapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class PdfController {

    private final PdfService pdfService;
    private final PdfToImageService pdfToImageService;
    private final GetPdfPages getPdfPages;

    @Autowired
    public PdfController(PdfService pdfService, PdfToImageService pdfToImageService, GetPdfPages getPdfPages) {
        this.pdfService = pdfService;
        this.pdfToImageService = pdfToImageService;
        this.getPdfPages = getPdfPages;
    }



    @PostMapping
    public void registerNewPdf(@RequestBody Pdfs pdfs){
        pdfService.savePdf(pdfs);
    }

    @GetMapping("/pdfs/names")
    public ResponseEntity<?> getPdfsNames() {
        List<Pdfs> pdfs = pdfService.getAllPdfs();
        List<String> pdfNames = new ArrayList<>();
        List<Long> pdfIds = new ArrayList<>();
        if (pdfs != null && !pdfs.isEmpty()) {
            for (Pdfs pdf : pdfs) {
                pdfNames.add(pdf.getName());
                pdfIds.add(pdf.getId());
            }
            return new ResponseEntity<>(pdfNames, HttpStatus.OK);
        } else {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "PDF dosyaları bulunamadı.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pdfs/ids")
    public ResponseEntity<?> getPdfsIds() {
        List<Pdfs> pdfs = pdfService.getAllPdfs();
        List<Long> pdfIds = new ArrayList<>();
        if (pdfs != null && !pdfs.isEmpty()) {
            for (Pdfs pdf : pdfs) {
                pdfIds.add(pdf.getId());
            }
            return new ResponseEntity<>(pdfIds, HttpStatus.OK);
        } else {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "PDF dosyaları bulunamadı.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pdfcounter/{id}")
    public ResponseEntity<Integer> getPageCount(@PathVariable Long id) throws IOException {
        Pdfs pdf = pdfService.getPdfById(id);
        if (pdf != null) {
            byte[] pdfBytes = pdf.getPdfByte();
            // PDF'yi görüntüye dönüştürün
            byte[] imageBytes = pdfToImageService.convertPdfToImage(pdfBytes);
            if (imageBytes != null) {
                int pageCount = getPdfPages.pagesOfPdf(pdfBytes);
                return new ResponseEntity<>(pageCount, HttpStatus.OK);

            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




    @GetMapping(value = "/pdf/{id}/{page}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getPdf(@PathVariable Long id, @PathVariable int page) throws IOException {
        Pdfs pdf = pdfService.getPdfById(id);
        if (pdf != null) {
            byte[] pdfBytes = pdf.getPdfByte();
            // PDF'yi görüntüye dönüştürün
            byte[] imageBytes = pdfToImageService.convertPdfToImage(pdfBytes,page);

            if (imageBytes != null) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_PNG);
                headers.setContentLength(imageBytes.length);
                return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);

            } else {
                return ResponseEntity.notFound().build();
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    // Diğer metotlar...
}
