News Parsing Command-Line Add-On
--------------------------------

This is an identical distribution of the version of the CLAVIN geo-parsing tool MIT C4CM 
uses, with the small addition of a command-line utility to output a JSON file containing
the results for a single file.  The setup instructions should be followed as described
below, and once installed


News Parsing Server
-------------------

A lightweight server to allow socket-based requests to the Stanford Named Entity 
Recognized and CLAVIN geoparser.  It allows you to submit unstructured text over a socket 
and a receive in reply JSON results with information about locations mentioned, people 
mentioned, and countries the text is "about".  The geoparsing is tuned to identify countries.

Installation
------------

You need maven and java.

On Ubuntu, make sure you do this:
```
sudo apt-get install maven2
sudo apt-get install openjdk-7-jdk
```

Separately, Maven can be downloaded here:
http://maven.apache.org/

Follow the "Installation Instructions section here:
http://maven.apache.org/download.cgi#Installation


How to build & use CLAVIN:
--------------------------

From your operating system's command line (instructions tested from OS/X)

1. Download the source code and unzip to your preferred location:
	> https://github.com/sandsfish/CLAVIN-Server/archive/master.zip

2. Move into the newly-created CLAVIN directory:
	> `cd CLAVIN`

3. Download the latest version of allCountries.zip gazetteer file from GeoNames.org:
	> `curl -O http://download.geonames.org/export/dump/allCountries.zip`

	If the above command fails, copy the URL into your browser location and hit enter.
	The file should download.  Move the downloaded 'allCountries.zip' to your CLAVIN
	directory created in step 1.

4. Unzip the GeoNames gazetteer file in your CLAVIN directory:
	> `unzip allCountries.zip`

5. Compile the source code:
	> `mvn compile`

6. Create the Lucene Index (this one-time process will take several minutes):
	> `MAVEN_OPTS="-Xmx2048M" mvn exec:java -Dexec.mainClass="com.bericotech.clavin.index.IndexDirectoryBuilder"`

7. Run the example program:
	> `./test.sh`
	(This should work from OS/X or Linux systems.  On Windows, try running the command directly:
	> `MAVEN_OPTS="-Xmx2048M" mvn exec:java -Dexec.mainClass="edu.mit.civic.mediacloud.CommandLineParser" -Dexec.args="'./src/test/resources/sample-docs/multi-country.txt'"`


Runnning from the Command Line
-------

In OS/X or Linux, use the convenience script, passing it the plain-text file that you wish to parse
	> `./parse.sh "path/to/my/file.txt"

On Windows, try running the full command, passing the path to your file in the final parameter, inside of the single quotes:
	> `MAVEN_OPTS="-Xmx2048M" mvn exec:java -Dexec.mainClass="edu.mit.civic.mediacloud.CommandLineParser" -Dexec.args="'path/to/my/file.txt'"`


Running in Server Mode
-------

Running this command will start up a server on port 8080.

```
mvn exec:java -Dexec.mainClass="edu.mit.civic.mediacloud.ParseServer" -Dexec.args="-Xmx2g"
```

Use
---

To test it out, hit this url in a browser and you should get some JSON back.

```
http://localhost:8080/parse?text=This is some text about New York City, and maybe about Harari as well
```

and to get some basic status hit this url:

```
http://localhost:8080/status
```
