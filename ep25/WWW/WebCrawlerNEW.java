/******************************************************************************
 *  Nome: Lucas Stefan Abe
 *  Nº USP: 8531612
 *  Compilação:  javac-algs4 WebCrawlerNEW.java 
 *  Execução:    java WebCrawlerNEW url num
 * 
 ******************************************************************************/

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Collections;
import java.util.ArrayList;

import edu.princeton.cs.algs4.*;

public class WebCrawlerNEW { 

    public static void main(String[] args) { 

        // timeout connection after 500 miliseconds
        /*System.setProperty("sun.net.client.defaultConnectTimeout", "500");
        System.setProperty("sun.net.client.defaultReadTimeout",    "500");*/

        // initial web page
        String s = args[0];
        int num = Integer.parseInt(args[1]), count = 1;

        // list of web pages to be examined
        Queue<String> q = new Queue<String>();
        q.enqueue(s);

        // existence symbol table of examined web pages
        SET<String> set = new SET<String>();
        set.add(s);

        ArrayList<String> links = new ArrayList<String>();
        links.add(args[0]);

        // breadth first search crawl of web
        while (!q.isEmpty() && count < num) {
            String v = q.dequeue();

            In in = new In(v);

            // only needed in case website does not respond
            if (!in.exists()) continue;

            String input = in.readAll();

            String  regexp  = "href=\"/[^\"]*";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(input);

            // find all matches
            while (matcher.find() && count < num) {
                String w = matcher.group();
                String link = args[0] + w.substring(6, w.length());
                if (!set.contains(link)) {
                    q.enqueue(link);
                    set.add(link);
                    links.add(link);
                    count++;
                }
            }
        }

        Collections.sort(links);
        for (String link : links)
            System.out.println(link);

   }
}
