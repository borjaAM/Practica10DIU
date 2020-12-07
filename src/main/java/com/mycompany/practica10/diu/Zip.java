package com.mycompany.practica10.diu;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.zip.*;
import java.util.List;

public class Zip {
    private final List<String> files;
    private final String directory;
    
    public Zip(List<String> files, String directory) {
        this.files = files;
        this.directory = directory;
    }
    
    public void comprimir(){
        int BUFFER_SIZE = 200;
        try {
            // Objeto para referenciar a los archivos que queremos comprimir
            BufferedInputStream origin = null;
            // Objeto para referenciar el archivo zip de salida
            FileOutputStream dest = new FileOutputStream(directory + ".zip");
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
            // Buffer de transferencia para almacenar datos a comprimir
            byte[] data = new byte[BUFFER_SIZE];
            Iterator i = files.iterator();
            while(i.hasNext()) {
                String filename = (String)i.next();
                FileInputStream fi = new FileInputStream(filename);
                origin = new BufferedInputStream(fi, BUFFER_SIZE);
                ZipEntry entry = new ZipEntry( filename );
                out.putNextEntry( entry );
                // Leemos datos desde el archivo origen y se envían al archivo destino
                int count;
                while((count = origin.read(data, 0, BUFFER_SIZE)) != -1) {
                    out.write(data, 0, count);
                }
                // Cerramos el archivo origen, ya enviado a comprimir
                origin.close();
            }
            // Cerramos el archivo zip
            out.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
