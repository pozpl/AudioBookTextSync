package ru.urbancamper.audiobookmarker;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.urbancamper.audiobookmarker.context.BeansAnnotations;
import ru.urbancamper.audiobookmarker.text.LanguageModelBasedTextTokenizer;


/**
 * Hello world!
 *
 */
public class App{

    /**
     *  Logging facility
     */
    protected final Log logger = LogFactory.getLog(getClass());


    private String[] getAudioFilesPaths(String directoryPath){
        File directory = new File(directoryPath);
        String[] filePathsList = directory.list();
        return filePathsList;
    }

    private String getBookFullText(String txtFilePath){
        StringBuilder strBuffer = new StringBuilder();
        int BLOC_SIZE = 512;
        char[] b = new char[BLOC_SIZE];
        Reader fileReader;
        try {
            fileReader = new FileReader(txtFilePath);
            int n;
            while((n = fileReader.read(b))>0){
                strBuffer.append(b, 0, n);
            }
        } catch (IOException ex) {
            this.logger.error("Excsption during file read " + txtFilePath + ": " + ex);
        }
        String retText = strBuffer.toString();
        return retText;
    }

    public static void main( String[] args ){

        ApplicationContext ctxt = new AnnotationConfigApplicationContext(BeansAnnotations.class);
        String text = "test, token model.";
        LanguageModelBasedTextTokenizer instance = ctxt.getBean(LanguageModelBasedTextTokenizer.class);

        String[] result = instance.tokenize(text);

        for(int tokenIndex = 0; tokenIndex < result
                .length; tokenIndex++){
            System.out.println(result[tokenIndex]);
        }
    }
}
