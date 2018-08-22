/******************************************************************************
 *  Compilation:  javac LinkFinder.java In.java
 *  Execution:    java LinkFinder url
 *  
 *  Downloads the web page and prints out all urls on the web page.
 *  Gives an idea of how Google's spider crawls the web. Instead of
 *  looking for hyperlinks, we just look for patterns of the form:
 *  http:// followed by any non-whitespace characters except ".
 *
 *  % java LinkFinder http://www.cs.princeton.edu
 *  http://www.w3.org/TR/REC-html40/loose.dtd 
 *  http://www.cs.princeton.edu/
 *  http://maps.yahoo.com/py/maps.py?addr=35+Olden+St&csz=Princeton,+NJ,+08544-2087
 *  http://www.princeton.edu/
 *  http://www.Princeton.EDU/Siteware/WeatherTravel.shtml
 *  http://ncstrl.cs.Princeton.EDU/
 *  http://www.genomics.princeton.edu/
 *  http://www.math.princeton.edu/PACM/
 *  http://libweb.Princeton.EDU/
 *  http://libweb2.princeton.edu/englib/
 *  http://search.cs.princeton.edu/query.html
 *
 ******************************************************************************/

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import edu.princeton.cs.algs4.*;

public class LinkFinder { 


   public static void main(String[] args) { 
      In in = new In(args[0]);
      String input = in.readAll();

     /*************************************************************
      *  \\S for not whitespace characters
      *  ^\" for all characters except "     (add ? to list...)
      *  && for intersection of two character classes
      *  + for one or more occurrences
      *************************************************************/
      String regexp = "http://[[\\S]&&[^\"]]+";
      Pattern pattern = Pattern.compile(regexp);
      Matcher matcher = pattern.matcher(input);
    
      // find and print all matches
      while (matcher.find()) 
         System.out.println(matcher.group());
   }

}
