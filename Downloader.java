package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
public class Downloader implements Runnable
{
    private String name;
    private final String toPath;
    public Downloader(String name, String toPath)
    {
        this.name = name;
        this.toPath = toPath;
    }

    @Override
    public void run()
    {
        try
        {
            DownloadFile(name, toPath);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public static void DownloadFile(String url, String path) throws MalformedURLException, FileNotFoundException, IOException
    {
        URL fileUrl = new URL(url);
        ReadableByteChannel rbc = Channels.newChannel(fileUrl.openStream());
        FileOutputStream fos = new FileOutputStream(path);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }
}

