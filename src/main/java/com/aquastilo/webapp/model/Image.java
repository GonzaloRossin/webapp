package com.aquastilo.webapp.model;

import jakarta.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_imageid_seq")
    @SequenceGenerator(sequenceName = "image_imageid_seq", name = "image_imageid_seq", allocationSize = 1)
    @Column(name = "imageId")
    private long id;

    @Column(name = "bitmap")
    private byte[] bytes;

    private String contentType;

    public Image() {
        //for hibernate
    }

    public Image(byte[] bytes, String contentType) {
        this.contentType = contentType;
        this.bytes = bytes;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
