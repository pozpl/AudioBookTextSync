package ru.urbancamper.audiobookmarker;

import org.apache.commons.cli.Options;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.urbancamper.audiobookmarker.context.BeansAnnotations;


/**
 * Hello world!
 *
 */
public class App{

    /**
     *  Logging facility
     */
    protected final Log logger = LogFactory.getLog(getClass());

    private void getCliOptions(String[] arg){
        Options options = new Options();
        options.addOption("a", "audio", true, "Path to a directory contaning audiobook files");

    }

    public static void main( String[] args ){
            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeansAnnotations.class);
        AudioBookMarkerUtil audioBookMarkerUtil;
        audioBookMarkerUtil = applicationContext.getBean(AudioBookMarkerUtil.class);

    }
}
