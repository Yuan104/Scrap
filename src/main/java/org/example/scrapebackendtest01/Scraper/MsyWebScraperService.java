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
public class MsyWebScraperService {
    @Autowired
    private UserRepository userRepository;

    public void scrapeAndStoreData(String baseUrl, String type, String company, String details) {

        int pageNumber = 1; // Start from the first page
        HashMap<String, Product> seenProducts = new HashMap<>(); // Track scraped products
        boolean scrapeStopFlag = false;
        Integer repeatTime = 0;

//        Boolean containQuestionMark = false;
//        if(baseUrl.contains("?"))
//        {
//            containQuestionMark = true;
//        }

        while (!scrapeStopFlag) {
//
//            String url;
//            if (containQuestionMark ==  true) {
//                url = baseUrl + "&pagenumber=" + pageNumber;
//            }else {
//                url = baseUrl + "?pagenumber=" + pageNumber;
//                System.out.println("Scraping page: " + url);
//            }

            String url = baseUrl.replaceAll("page=\\d+", "page=" + pageNumber);
            System.out.println("Scraping page: " + url);

            try {
                Document document = Jsoup.connect(url)
                        .userAgent(getRandomUserAgent()) // Rotate user-agents
                        .header("Cache-Control", "no-cache") // Force fresh request
                        .get();

                // Print out the full HTML of the scraped page for debugging
//                System.out.println("==== HTML Content of Page " + pageNumber + " ====");
//                System.out.println(document.html());
//                System.out.println("============================================");

                // Find all product items
                Elements productItems = document.select("li.goods_info");

                // Break the loop if no products are found (end of pagination)
                if (productItems.isEmpty()) {
                    System.out.println("No more products found. Stopping scraper.");
                    break;
                }

                // Extract information from each product item
                for (Element item : productItems) {
                    // Extract the product name
                    Element nameTag = item.selectFirst("div.goods_name a span[itemprop=name]");
                    String name = nameTag != null ? nameTag.text().trim() : "N/A";

                    // Extract the price
                    Element priceTag = item.selectFirst("span.goods-price[itemprop=price]");
                    String price = priceTag != null ? "$" + priceTag.text().trim() : "N/A";

                    // Extract the image URL
                    Element imgTag = item.selectFirst("div.goods_img picture img");
                    String imageUrl = imgTag != null ? imgTag.attr("src").trim() : "N/A";

                    // Extract the product link
                    Element linkTag = item.selectFirst("div.goods_name a");
                    String productLink = linkTag != null ? "https://www.msy.com.au" + linkTag.attr("href").trim() : "N/A";

                    // Extract stock status
                    Element stockTag = item.selectFirst("span.goods_stock");
                    String stockStatus = "Unknown"; // Default value
                    if (stockTag != null) {
                        stockStatus = stockTag.text().trim(); // Extract text from stock span
                    }

                    System.out.println("Product: " + name + " | Price: " + price + " | Stock: " + stockStatus);

                    // Detect repeated products
                    if (seenProducts.containsKey(name)) {
                        repeatTime++;
                        System.out.println("Repeated product detected: " + name + " (" + repeatTime + " times)");
                        if (repeatTime >= 50) {
                            scrapeStopFlag = true;
                            System.out.println("Repeated product limit reached. Stopping scraper.");
                            break;
                        }
                    } else {
                        // Save product details to the database
                        Product product = new Product();
                        product.setName(name);
                        product.setPrice(price);
                        product.setCompany(company);
                        product.setType(type);
                        product.setImageUrl(imageUrl);
                        product.setProductLink(productLink);
                        product.setDetails(details);
                        product.setStockStatus(stockStatus);
                        userRepository.save(product);
                        seenProducts.put(name, product);
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

    // Random user-agent generator to avoid caching
    private String getRandomUserAgent() {
        String[] userAgents = {
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36",
                "Mozilla/5.0 (iPhone; CPU iPhone OS 15_0 like Mac OS X) AppleWebKit/537.36 (KHTML, like Gecko) Version/15.0 Mobile/15E148 Safari/537.36"
        };
        return userAgents[(int) (Math.random() * userAgents.length)];
    }
}