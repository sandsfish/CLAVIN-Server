package edu.mit.civic.mediacloud;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import com.bericotech.clavin.resolver.ResolvedLocation;
import com.bericotech.clavin.util.TextUtils;

/**
 * Command-line tool to parse locations and return as JSON
 */
public class CommandLineParser {

    /**
     * A utility to run parse from the command-line on a single specified 
     * file and save the results as JSON.
     *
     * Yes, it's quite inefficient to load the entire HTTP server, etc.
     * each time you parse a file.  :)
     *
     * @author Sands Fish <sands@mit.edu>
     * 
     * @param args Path to file to parse as string from command-line
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        
        // Unstructured text file specified at command-line
        File inputFile = new File(args[0]);
        
        // Grab the contents of the text file as a String
        String inputString = TextUtils.fileToString(inputFile);

        // Parse location names in the next and return entities as JSON
        String resultsJSON = ParseManager.parse(inputString);

        // Output JSON to date-stamped file
        long lDateTime = new Date().getTime();
        String outFilename = new String("geo_parse-" + lDateTime + ".json");
        System.out.println("\n\nSaving results to file " + outFilename + "\n\n");
        PrintWriter out = new PrintWriter(outFilename);
        out.println(resultsJSON);
        out.close();
    }
}