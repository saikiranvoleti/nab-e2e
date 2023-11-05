package utils;

import listener.CucumberBaseTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReporterLog {
    public static void lotText(String text){

        CucumberBaseTest.scenarios.get().log(text);

    }
    public  static  void  logImage(String name, String path){
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(path));
            CucumberBaseTest.scenarios.get().attach(fileBytes, "image/png", name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void logFilepath(String name,String path){
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(path));
        CucumberBaseTest.scenarios.get().attach(fileBytes, "text/plain", name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        }

}
