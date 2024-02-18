package com.aquastilo.webapp.dto;

import com.aquastilo.webapp.model.Image;
import com.aquastilo.webapp.utils.ImageUtils;

public class ImageDto {

    private long id;
    private byte[] bitMap;

    public static ImageDto fromImage(Image image) {
        ImageDto imageDto = new ImageDto();

        imageDto.id = image.getId();
        //imageDto.bitMap = ImageUtils.decompressImage(image.getBytes());
        imageDto.bitMap = image.getBytes();

        return imageDto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getBitMap() {
        return bitMap;
    }

    public void setBitMap(byte[] bitMap) {
        this.bitMap = bitMap;
    }
}
