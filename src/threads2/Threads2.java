/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Hvis man bruger 'run' så kører det halvt så hurtigt (ca), da den sætter en tråd igang og venter til den er færdig og så bagefter sætter næste tråd igang.
// I 'start' sætter den alle tråde igang på samme tid og de kører derfor parallelt og man udnytter på denne måde flere kerner.
// De udkommenterede kodelinier 38-40 kører tråden som start og kodelinierne 43-45 som run.

package threads2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/** 
 *
 * @author Rasmus
 */
public class Threads2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());

        int total = 0;

        Threadurl1 t1 = new Threadurl1("https://fronter.com/cphbusiness/design_images/images.php/Classic/login/fronter_big-logo.png");
        Threadurl2 t2 = new Threadurl2("https://fronter.com/cphbusiness/design_images/images.php/Classic/login/folder-image-transp.png");
        Threadurl3 t3 = new Threadurl3("https://fronter.com/volY12-cache/cache/img/design_images/Classic/other_images/button_bg.png");

//        t1.start();
//        t2.start();
//        t3.start();
        
        long start = System.nanoTime();
        t1.run();
        t2.run();
        t3.run();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Threads2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        long end = System.nanoTime();
        System.out.println("Time Sequental: " + (end - start));
        
        total += t1.getSum1();
        total += t2.getSum2();
        total += t3.getSum3();

        System.out.println("-------------------");
        System.out.println("Ialt : " + total);

    }
}

class Threadurl1 extends Thread {

    String url1;
    int sum1 = 0;

    public Threadurl1(String url1) {
        this.url1 = url1;
    }

    public byte[] getBytesFromUrl(String url) {
        ByteArrayOutputStream bis = new ByteArrayOutputStream();
        try {
            InputStream is = new URL(url).openStream();
            byte[] bytebuff = new byte[4096];
            int read;
            while ((read = is.read(bytebuff)) > 0) {
                bis.write(bytebuff, 0, read);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return bis.toByteArray();
    }

    @Override
    public void run() {
        for (byte a1 : getBytesFromUrl(url1)) {
            sum1 += a1;
        }
        System.out.println("Url 1: " + sum1);
    }

    public int getSum1() {
        return sum1;
    }
}

class Threadurl2 extends Thread {

    String url2;
    int sum2 = 0;

    public Threadurl2(String url2) {
        this.url2 = url2;
    }

    public byte[] getBytesFromUrl(String url) {
        ByteArrayOutputStream bis = new ByteArrayOutputStream();
        try {
            InputStream is = new URL(url).openStream();
            byte[] bytebuff = new byte[4096];
            int read;
            while ((read = is.read(bytebuff)) > 0) {
                bis.write(bytebuff, 0, read);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return bis.toByteArray();
    }

    @Override
    public void run() {
        for (byte a2 : getBytesFromUrl(url2)) {
            sum2 += a2;
        }
        System.out.println("Url 2: " + sum2);
    }

    public int getSum2() {
        return sum2;
    }
}

class Threadurl3 extends Thread {

    String url3;
    int sum3 = 0;

    public Threadurl3(String url3) {
        this.url3 = url3;
    }

    public byte[] getBytesFromUrl(String url) {
        ByteArrayOutputStream bis = new ByteArrayOutputStream();
        try {
            InputStream is = new URL(url).openStream();
            byte[] bytebuff = new byte[4096];
            int read;
            while ((read = is.read(bytebuff)) > 0) {
                bis.write(bytebuff, 0, read);
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }

        return bis.toByteArray();
    }

    @Override
    public void run() {
        for (byte a3 : getBytesFromUrl(url3)) {
            sum3 += a3;
        }
        System.out.println("Url 3: " + sum3);
    }

    public int getSum3() {
        return sum3;
    }

}


