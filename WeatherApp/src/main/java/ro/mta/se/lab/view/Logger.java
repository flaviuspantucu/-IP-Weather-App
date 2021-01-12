package ro.mta.se.lab.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    /**
     * Singleton instance of the class
     */
    private static Logger instance = null;  // singleton object
    /**
     * The outFile to log if this is set
     */
    private FileWriter outputFile;       // the writer who will do the job done writing output

    /**
     * Logger class constructor
     */
    private Logger() {
        this.outputFile = null;
    }

    public synchronized static Logger getInstance() {
        // verify if the singleton object is already created
        if (instance == null)
            instance = new Logger();
        return instance;
    }

    /**
     * Sets the new outFile for the logger to print to.
     *
     * @param filePath new file to set the output to.
     */
    public synchronized void setOutputFile(String filePath) throws IOException {
        //Close the old file if existent
        if (this.outputFile != null)
            this.outputFile.close();
        File out = new File(filePath);
        out.createNewFile();
        outputFile = new FileWriter(out, true);
    }

    /**
     * Write output on the the specified file
     *
     * @param message  string that we have to display
     * @param filePath path to the file we need to write into
     */
    public synchronized void write(String message, String filePath, String _city) throws IOException {
        //Format message
        String timeStamp = new SimpleDateFormat("[ dd.MM.yyyy - HH.mm.ss ] ").format(new Date());
        message = timeStamp + _city + " \n" + message;

        File out = new File(filePath);
        // creates a FileWriter Object and write output
        FileWriter localOutFile = new FileWriter(out,true);
        localOutFile.write(message + '\n');
        localOutFile.close();
    }
}