package org.newest.testwarapp;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfToImageService {
    public byte[] convertPdfToImage(byte[] pdfBytes, int pageIndex) {
        try {
            PDDocument document = PDDocument.load(pdfBytes);
            PDFRenderer renderer = new PDFRenderer(document);

            int pageCount = document.getNumberOfPages();

            if (pageCount == 0) {
                return null;
            }

            BufferedImage image = renderer.renderImageWithDPI(pageIndex, 300, ImageType.RGB);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            javax.imageio.ImageIO.write(image, "png", baos);

            document.close();

            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] convertPdfToImage(byte[] pdfBytes) {
        try {
            PDDocument document = PDDocument.load(pdfBytes);
            PDFRenderer renderer = new PDFRenderer(document);

            int pageCount = document.getNumberOfPages();

            if (pageCount == 0) {
                return null;
            }

            BufferedImage image = renderer.renderImageWithDPI(0, 300, ImageType.RGB);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            javax.imageio.ImageIO.write(image, "png", baos);

            document.close();

            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
