package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileReader
{
    private Map<String, String> map;
    public FileReader()
    {
        map = new HashMap<>();
    }
    public void Init(int threads, String folderName, String fileName) throws Exception
    {
        CreateDir(folderName);
        map = ReadFiles(fileName);
        if (!map.isEmpty())
        {
            ExecutorService pool = Executors.newFixedThreadPool(threads);
            for (Map.Entry<String, String> entry : map.entrySet())
            {
                pool.submit(new Downloader(entry.getKey(), folderName + File.separator + entry.getValue()));
            }
            pool.shutdown();
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        }
        else
        {
            System.out.println("file is empty");
        }
    }
    public Map<String, String> ReadFiles(String filePath)
    {
        try
        {

            FileInputStream fis=new FileInputStream(filePath);
            Scanner sc=new Scanner(fis);
            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                if (line.length() < 5) break;
                System.out.println(line);
                map.put(line.split(" ")[0], line.split(" ")[1]);
            }
            sc.close();
        }
        catch(IOException e)
        {
            System.out.println("exception " + e.getMessage());
            e.printStackTrace();
        }
        return map;
    }
    public void CreateDir(String dirName) throws Exception
    {
        try
        {
            new File(dirName).mkdirs();
        }
        catch (Exception ex)
        {
            throw new Exception("can't create directory");
        }
    }
    private void close() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
