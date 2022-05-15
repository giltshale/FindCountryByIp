import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends JFrame {
    public static int WIDTH=500;
    public static int HEIGHT=200;


    public Main() {
        JFrame frame = new JFrame("Ip Address Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Window panel = new Window();
        this.getContentPane().add(panel);
        this.pack();
        this.setSize(WIDTH,HEIGHT);
        this.setResizable(true);toBack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

}