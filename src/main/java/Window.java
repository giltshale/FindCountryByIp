import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Window extends JPanel implements ActionListener {

    private JLabel inputLabel, outputLabel, resultLabel;
    private JTextField ipInputFrame;
    private String countryName;
    Button enterButton;

    public Window() {
        enterButton = new Button();
        inputLabel = new JLabel("Enter Ip Address Separated by point ('.') : ");
        enterButton.setLabel("Press 'Enter' on keyBord");
        enterButton.setPreferredSize(new Dimension(150,50));

        outputLabel = new JLabel("\n" + "This Ip Address belongs to : ");
        resultLabel = new JLabel("---");

        ipInputFrame = new JTextField(50);
        ipInputFrame.addActionListener(this);

        add(inputLabel);
        add(ipInputFrame);
        add(enterButton);
        add(outputLabel);
        add(resultLabel);
        this.setOpaque(true);
        this.setEnabled(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.cyan);
    }

    public void findCountryNameByIp(String userIpInput) {

        String url = "https://www.xmyip.com";
        boolean found = false;
        enterButton.setLabel("Loading, Please wait...");
        String firstByte = this.ipInputFrame.getText().split("\\.")[0];
        String secByte = this.ipInputFrame.getText().split("\\.")[1];
        String bothBytes = firstByte + "." + secByte;
        System.out.println(bothBytes);

        try {
            Document document = Jsoup.connect("https://www.xmyip.com/ip-blocks-countries").get();
            Elements countryElements = document.getElementsByClass("divTableCell");

            for (Element country : countryElements) {
                Elements aTags = country.getElementsByTag("a");
                String link = aTags.attr("href");
                System.out.println(country.text());
                System.out.println(url + link);
                //enter to each countryCard
                Document allCountryIp = Jsoup.connect(url + link).get();
                Elements ipAddressRange = allCountryIp.getElementsByClass("divTableCell");
                String string = (Arrays.toString(
                        (ipAddressRange.select("a").text().replaceAll("[^0-9?!\\.]", " ")).split("\\s+")));


                ArrayList<String> ipAddresses = new ArrayList<>((Arrays.asList(string.split(","))));
                ipAddresses.add(string);

                //test
                System.out.println("the whole IP addresses size is: " + ipAddresses.size() + " and there are they: " +
                        ipAddresses);

                for (String str : ipAddresses) {
                    //trim each ip address
                    str = str.trim();
                    if (str.startsWith(bothBytes)) {
                        this.countryName = country.text();
                        // System.out.println("Loading.....");
                        System.out.println("This ip belongs to: " + (country.text()));
                        found = true;
                       enterButton.setLabel("Press 'Enter' on keyBord");
                       enterButton.addActionListener(this);
                        break;
                    }
                }
                if (!found) {
                    System.out.println("we cant find this ip address");

                    //stop searching when we found the IP Country
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    // Represents an action listener for the ip input field.

    public void actionPerformed(ActionEvent e) {
        System.out.println("======================================================================");

        findCountryNameByIp(ipInputFrame.getText());
        resultLabel.setText(this.countryName);
        enterButton.setLabel("Press 'Enter' on keyBord");
    }

}

