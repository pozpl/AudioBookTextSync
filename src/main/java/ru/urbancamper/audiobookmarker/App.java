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
 * Main application
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
            options.addOption("m", "marked_text", true, "File path to put marked text in.");
            options.addOption("h", false, "Print help and exit");
            CommandLineParser parser = new PosixParser();
            cmd = parser.parse(options, args);
        } catch (ParseException ex) {
            logger.error("Error while parsing cli arguments" + ex);
        }
        return cmd;
    }

    private static void printHelp(){
        System.out.println("a - audio dir path\n"
                + "b - book file path \n"
                + "m - path to marked text\n");
    }

    public static void main(String[] args) {
        App app = new App();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeansAnnotations.class);
        AudioBookMarkerUtil audioBookMarkerUtil;
        audioBookMarkerUtil = applicationContext.getBean(AudioBookMarkerUtil.class);

        CommandLine cmd = app.getCliOptions(args);
        if(cmd.hasOption("h")){
            App.printHelp();
            return;
        }

        String audioBookDirPath = cmd.getOptionValue("a");
        String bookFilePath = cmd.getOptionValue("b");
        app.logger.info("Run with a audio book path " + audioBookDirPath);
        app.logger.info("Run with a book file path " + bookFilePath);
        MarkedDocument markedDoc = audioBookMarkerUtil.makeMarkers(audioBookDirPath, bookFilePath);
        String markedText = markedDoc.getMarkedText();
        app.logger.info("A marked text obtained ");
        String markedTextFilePath = cmd.getOptionValue("m");
        app.logger.info("A marked text file path " + markedTextFilePath);
        if(markedTextFilePath != null){
            audioBookMarkerUtil.writeMarkedTextToFile(markedTextFilePath, markedText);
            app.logger.info("Write marked text into file " + markedTextFilePath);
        }

    }
}
