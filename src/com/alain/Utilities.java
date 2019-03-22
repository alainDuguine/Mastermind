package com.alain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utilities {

    private static final Logger logger = LogManager.getLogger();

    public Properties connectFileProperties(){
        Properties p = new Properties();

        InputStream is = null;
        try {
            is = new FileInputStream("src/resources/config.properties");
        } catch (FileNotFoundException e) {
            logger.error("Fichier introuvable", e);
        }
        try {
            p.load(is);
        } catch (IOException e) {
            logger.error(e);
        }
        return p;
    }
}
