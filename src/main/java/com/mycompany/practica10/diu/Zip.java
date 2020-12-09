package com.mycompany.practica10.diu;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
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
    
    public int comprimir(){
        int BUFFER_SIZE = 1024;
        try {
            // Objeto para referenciar a los archivos que queremos comprimir
            BufferedInputStream origin = null;
            // Objeto para referenciar el archivo zip de salida
            File m = new File(directory);
            
            FileOutputStream dest = new FileOutputStream(directory + ".zip");
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
            // Buffer de transferencia para almacenar datos a comprimir
            byte[] data = new byte[BUFFER_SIZE];
            Iterator i = files.iterator();
            while(i.hasNext()) {
                String filepath = (String) i.next();
                File file = new File(filepath);
                FileInputStream fi = new FileInputStream(filepath);
                origin = new BufferedInputStream(fi, BUFFER_SIZE);
                ZipEntry entry = new ZipEntry( file.getName() );
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
            return 0;
        } catch(IOException e){
            e.printStackTrace();
            return -1;
        }
    }
    
}
