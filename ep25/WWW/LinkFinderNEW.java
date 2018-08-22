/******************************************************************/
/* Nome: Lucas Stefan Abe
/* Nº USP: 8531612
/*
/* Compilação: javac-algs4 LinkFinderNEW.java
/* Execução: java-algs4 LinkFinderNEW url
/*           java-algs4 LinkFinderNEW -s url
/******************************************************************/

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Collections;
import java.util.ArrayList;

import edu.princeton.cs.algs4.*;

public class LinkFinderNEW { 

   public static void main(String[] args) { 
      In in;
      ArrayList<String> links = new ArrayList<String> ();
      if (args.length == 1) 
         in = new In(args[0]);
      else 
         in = new In(args[1]);
      String input = in.readAll();

      /* é necessário o link começar com / ou http pois senão
         os javascrits serão pegos*/
      String regexp = "href=\"((http)|/)[^\"]*";
      Pattern pattern = Pattern.compile(regexp);
      Matcher matcher = pattern.matcher(input);
    
      while (matcher.find()) {
         String s = matcher.group();
         String subs = s.substring(6, s.length());
         if (subs.charAt (0) == '/') {
            if (args.length == 1)
               System.out.println(args[args.length-1] + subs);
            else if (!links.contains(args[args.length-1] + subs))
               links.add(args[args.length-1] + subs);
         }
         else {
            if (args.length == 1)
               System.out.println(subs);
            else if (!links.contains(subs))
               links.add(subs);
         }
      }

      if (args.length == 2) {
         Collections.sort (links);
         for (String link : links)
            System.out.println(link);
      }
   }

}