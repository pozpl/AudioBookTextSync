package ru.urbancamper.audiobookmarker;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.urbancamper.audiobookmarker.context.BeansAnnotations;
import ru.urbancamper.audiobookmarker.document.MarkedDocument;

/**
 * Hello world!
 *
 */
public class App {

    /**
     * Logging facility
     */
    protected final Log logger = LogFactory.getLog(getClass());

    private CommandLine getCliOptions(String[] args) {
        CommandLine cmd = null;
        try {
            Options options = new Options();
            options.addOption("a", "audio", true, "Path to a directory contaning audiobook files");
            options.addOption("b", "book", true, "A txt File that contains book text.");
            options.addOption("m", "marked_text", false, "File path to put marked text in.");

            CommandLineParser parser = new PosixParser();
            cmd = parser.parse(options, args);
        } catch (ParseException ex) {
            logger.error("Error while parsing cli arguments" + ex);
        }
        return cmd;
    }



    public static void main(String[] args) {
        App app = new App();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeansAnnotations.class);
        AudioBookMarkerUtil audioBookMarkerUtil;
        audioBookMarkerUtil = applicationContext.getBean(AudioBookMarkerUtil.class);

        CommandLine cmd = app.getCliOptions(args);
        String audioBookDirPath = cmd.getOptionValue("a");
        String bookFilePath = cmd.getOptionValue("b");
        app.logger.info("Run with audio book path " + audioBookDirPath);
        app.logger.info("Run with book file path " + bookFilePath);
        MarkedDocument markedDoc = audioBookMarkerUtil.makeMarkers(audioBookDirPath, bookFilePath);
        String markedText = markedDoc.getMarkedText();

    }
}
