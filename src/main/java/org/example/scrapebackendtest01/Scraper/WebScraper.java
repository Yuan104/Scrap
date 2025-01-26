package org.example.scrapebackendtest01.Scraper;

import org.example.scrapebackendtest01.Entity.Product;
import org.example.scrapebackendtest01.Repository.UserRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.*;

@Component
public class WebScraper {
    @Autowired
    private UserRepository userRepository;

    public void scrapeAndStoreData(String baseUrl, String type, String company, String details) {
        int pageNumber = 1; // Start from the first page
        HashMap<String, Product> seenProducts = new HashMap<>(); // Track scraped products
        boolean scrapeStopFlag = false;
        Integer repeatTime = 0;
        Boolean containQuestionMark = false;
        if(baseUrl.contains("?"))
        {
         containQuestionMark = true;
        }

        while (!scrapeStopFlag) {
            String url;
            if (containQuestionMark ==  true) {
                url = baseUrl + "&pagenumber=" + pageNumber;
            }else {
                url = baseUrl + "?pagenumber=" + pageNumber;
                System.out.println("Scraping page: " + url);
            }

            try {
                // Fetch and parse the HTML content of the page
                Document document = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
                        .get();

                // Find all product boxes
                Elements productBoxes = document.select("div.prbox_box");

                // Break the loop if no products are found (end of pagination)
                if (productBoxes.isEmpty()) {
                    System.out.println("productBoxes.isEmpty");
                    break;
                }

                // Counter for detecting duplicate pages
                int newProductsCount = 0;

                // Extract information from each product box
                for (Element box : productBoxes) {
                    // Extract the CPU name
                    Element nameTag = box.selectFirst("div.prbox_name");
                    String name = nameTag != null ? nameTag.text().trim() : "N/A";

                    // Extract the price
                    Element priceTag = box.selectFirst("div.saleprice");
                    String price = priceTag != null ? priceTag.text().trim() : "N/A";

                    // Extract the image URL
                    String imageUrl = box.attr("data-lazy");
                    imageUrl = imageUrl.startsWith("url(") && imageUrl.endsWith(")") ?
                            imageUrl.substring(4, imageUrl.length() - 1) : imageUrl;

                    // Extract the product link
                    Element linkTag = box.selectFirst("a.prbox_link");
                    String productLink = linkTag != null
                            ? "https://www.centrecom.com.au" + linkTag.attr("href").trim()
                            : "N/A";

                    // Save the information to the database
                    Product product = new Product(); // Replace `User` with the appropriate entity
                    product.setName(name);
                    product.setPrice(price); // Example: Store price in the email field temporarily
                    if(seenProducts.containsKey(name))
                    {
                        repeatTime++;
                        System.out.println("repeated product occour " + name + repeatTime);

                        if(repeatTime >= 5)
                        {
                            scrapeStopFlag = true;
                            System.out.println("repeated time = 3 stop");
                            break;
                        }
                    }else {
//                        User user1 = userService.createUser(name, price);
                        Product productTemp = new Product();
                        productTemp.setName(name);
                        productTemp.setPrice(price);
                        productTemp.setCompany(company);
                        productTemp.setType(type);
                        productTemp.setImageUrl(imageUrl);
                        productTemp.setProductLink(productLink);
                        productTemp.setDetails(details);
                        userRepository.save(productTemp);
                        seenProducts.put(name, productTemp);
                    }
                }

            } catch (IOException e) {
                System.out.println("Failed to retrieve page " + pageNumber + ": " + e.getMessage());
                break;
            }

            // Move to the next page
            pageNumber++;
        }
    }
}

//@Component
//public class WebScraper {
//
//    @Autowired
//    private UserService userService;
//
//    public void scrapeAndStoreData(String baseUrl) {
//        int pageNumber = 1; // Start from the first page
//        Set<User> seenProducts = new HashSet<>(); // Track scraped products
//
//        while (true) {
//            String url = baseUrl + "?pagenumber=" + pageNumber;
//            System.out.println("Scraping page: " + url);
//
//            try {
//                // Fetch and parse the HTML content of the page
//                Document document = Jsoup.connect(url)
//                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
//                        .get();
//
//                // Find all product boxes
//                Elements productBoxes = document.select("div.prbox_box");
//
//                // Break the loop if no products are found (end of pagination)
//                if (productBoxes.isEmpty()) {
//                    System.out.println("productBoxes.isEmpty");
//                    break;
//                }
//
//                // Counter for detecting duplicate pages
//                int newProductsCount = 0;
//
//                // Extract information from each product box
//                for (Element box : productBoxes) {
//                    // Extract the CPU name
//                    Element nameTag = box.selectFirst("div.prbox_name");
//                    String name = nameTag != null ? nameTag.text().trim() : "N/A";
//
//                    // Extract the price
//                    Element priceTag = box.selectFirst("div.saleprice");
//                    String price = priceTag != null ? priceTag.text().trim() : "N/A";
//
//                    // Save the information to the database
//                    User user = new User(); // Replace `User` with the appropriate entity
//                    user.setName(name);
//                    user.setEmail(price); // Example: Store price in the email field temporarily
//                    if(seenProducts.contains(user))
//                    {
//                        System.out.println("repeated product occour , stop");
//                        break;
//                    }else {
//                        User user1 = userService.createUser(name, price);
//                        seenProducts.add(user1);
//                    }
//                }
//
//            } catch (IOException e) {
//                System.out.println("Failed to retrieve page " + pageNumber + ": " + e.getMessage());
//                break;
//            }
//
//            // Move to the next page
//            pageNumber++;
//        }
//    }
//}
