package org.newest.testwarapp;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name="pdfs")
public class Pdfs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pdfs_sequence")
    @SequenceGenerator(name = "pdfs_sequence", sequenceName = "pdfs_sequence")
  private Long id;
  private String name;
  private byte[] pdfByte;

    public Pdfs() {
    }

    public Pdfs(Long id, String name, byte[] pdfByte) {
        this.id = id;
        this.name = name;
        this.pdfByte = pdfByte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPdfByte() {
        return pdfByte;
    }

    public void setPdfByte(byte[] pdfByte) {
        this.pdfByte = pdfByte;
    }

    @Override
    public String toString() {
        return "Pdfs{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pdfByte=" + Arrays.toString(pdfByte) +
                '}';
    }

    public Pdfs(String name, byte[] pdfByte) {
        this.name = name;
        this.pdfByte = pdfByte;
    }
}
