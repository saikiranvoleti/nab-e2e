package utils;

import listener.CucumberBaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileWriterUtil {
    private static final Logger logger = LogManager.getLogger();

    public void writeToFile(String filename,String heading, List<String> text){
        try {
            String path =CucumberBaseTest.reportFolderPath+"/"+filename+".txt";
            Path filepath = Paths.get(path);

            if (!Files.exists(filepath)) {
                Files.createFile(filepath);
                logger.info ("File created: " + path);
            }
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(heading+"\n");
            for (String data:text) {
                fileWriter.write(data+"\n");
            }
            fileWriter.close();
            ReporterLog.logFilepath(filename,path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
