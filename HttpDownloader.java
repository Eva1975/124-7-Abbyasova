package com.company;

import java.util.HashMap;
import java.util.Map;

public class HttpDownloader
{
    Map<String, String> map = new HashMap<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        if(args.length != 3)
        {
            System.out.println("usage: java -jar HttpDownloader.jar 5 directory file_list.txt");
            return;
        }
        System.out.println("threads: " + args[0]);
        System.out.println("folder: " + args[1]);
        System.out.println("file: " + args[2]);
        try
        {
            FileReader fl = new FileReader();
            fl.Init(Integer.parseInt(args[0]),args[1],args[2]);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}

