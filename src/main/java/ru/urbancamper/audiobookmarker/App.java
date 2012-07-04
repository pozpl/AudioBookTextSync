package ru.urbancamper.audiobookmarker;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.urbancamper.audiobookmarker.context.BeansAnnotations;
import ru.urbancamper.audiobookmarker.text.LanguageModelBasedTextTokenizer;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {

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
