package org.newest.testwarapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PdfService {
    @Autowired
    private PdfRepository pdfRepository;

    @Autowired
    public PdfService(PdfRepository pdfRepository) {
        this.pdfRepository = pdfRepository;
    }

    public Pdfs savePdf(Pdfs pdfs) {
        return pdfRepository.save(pdfs);
    }

    public List<Pdfs> getAllPdfs() {
        return pdfRepository.findAll();
    }

    public Pdfs getPdfById(Long id) {
        // Belirli bir ID'ye sahip PDF'yi getir
        return pdfRepository.findById(id).orElse(null);
    }


    // Diğer işlemler...
}
