package ru.urbancamper.audiobookmarker;

import java.io.File;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.urbancamper.audiobookmarker.context.BeansAnnotations;
import ru.urbancamper.audiobookmarker.text.LanguageModelBasedTextTokenizer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Hello world!
 *
 */
public class App{
    private String[] getAudioFilesPaths(String directoryPath){
        File directory = new File(directoryPath);
        String[] filePathsList = directory.list();
        return filePathsList;
    }

    private String getBookFullText(String txtFilePath){
        //TODO IMplement this
        return null;
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
