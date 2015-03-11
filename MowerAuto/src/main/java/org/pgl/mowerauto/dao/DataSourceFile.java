package org.pgl.mowerauto.dao;

import java.io.File;

/**
 * A data source type of file.
 * */
public class DataSourceFile implements DataSource {
    private File file;
    
    public DataSourceFile(File file){
        this.file = file;
    }
    
    public File getFile() {
        return file;
    }
}
