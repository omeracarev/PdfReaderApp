package org.newest.testwarapp;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GetPdfPages {
    public int pagesOfPdf(byte[] pdfBytes) throws IOException {
        PDDocument document = PDDocument.load(pdfBytes);
        int pageCount = document.getNumberOfPages();

        if (pageCount == 0) {
            document.close();
            return 0;
        } else {
            document.close();
            return pageCount;
        }

    }
}
