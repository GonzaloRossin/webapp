package com.aquastilo.webapp.utils;

import java.io.ByteArrayOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {

    public static byte[] compressImage(byte[] data){
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];

        while(!deflater.finished()){
            int size = deflater.deflate(tmp);
            outputStream.write(tmp,0,size);
        }
        try{
            outputStream.close();
        }catch (Exception e){
            Logger.getAnonymousLogger().log(Level.WARNING,
                    "image compression exception");
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data){
        Inflater inflater = new Inflater();
        inflater.setInput(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];

        try{
            while(!inflater.finished()){
                int size = inflater.inflate(tmp);
                outputStream.write(tmp, 0, size);
            }
            outputStream.close();
        }catch (Exception e){
            Logger.getAnonymousLogger().log(Level.WARNING,
                    "image decompression exception");
        }

        return outputStream.toByteArray();
    }
}
