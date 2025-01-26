package org.example.scrapebackendtest01.Controller;

import org.example.scrapebackendtest01.Scraper.MsyWebScraperService;
import org.example.scrapebackendtest01.Scraper.WebScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scraper")
public class ScraperController {

    @Autowired
    private WebScraper webScraper;

    @Autowired
    private MsyWebScraperService msyWebScraperService;

    @GetMapping("/start")
//    public String startScraper(@RequestParam String baseUrl) {
      public String startScraper() {
        //CENTRECOM

        //CASES
        webScraper.scrapeAndStoreData("https://www.centrecom.com.au/cases-enclosures", "cases", "centrecom", null);

        //CPU
        webScraper.scrapeAndStoreData("https://www.centrecom.com.au/cpu-processors?specs=1685", "cpu", "centrecom", "AM4");
        webScraper.scrapeAndStoreData("https://www.centrecom.com.au/cpu-processors?specs=1686", "cpu", "centrecom", "AM5");
        webScraper.scrapeAndStoreData("https://www.centrecom.com.au/cpu-processors?specs=1684", "cpu", "centrecom", "LGA1700");
        webScraper.scrapeAndStoreData("https://www.centrecom.com.au/cpu-processors?specs=2038", "cpu", "centrecom", "LGA1851");

        //CPU COOLERS
        webScraper.scrapeAndStoreData("https://www.centrecom.com.au/cooling?specs=807", "cpu coolers", "centrecom", "Liquid coolers");
        webScraper.scrapeAndStoreData("https://www.centrecom.com.au/cooling?specs=808", "cpu coolers", "centrecom", "AIR coolers");

        //GPU
        webScraper.scrapeAndStoreData("https://www.centrecom.com.au/nvidia-amd-graphics-cards", "GPU", "centrecom", null);

        //SSD
        webScraper.scrapeAndStoreData("https://www.centrecom.com.au/internal-storage", "SSD", "centrecom", null);

        //MEMORY
        webScraper.scrapeAndStoreData("https://www.centrecom.com.au/memory-ram?specs=904", "Memory (RAM)", "centrecom", "DDR5");
        webScraper.scrapeAndStoreData("https://www.centrecom.com.au/memory-ram?specs=466", "Memory (RAM)", "centrecom", "DDR4");

        //MOTHER BOARD
        webScraper.scrapeAndStoreData("https://www.centrecom.com.au/motherboards?specs=760", "MOTHERBOARD", "centrecom", "AM4");
        webScraper.scrapeAndStoreData("https://www.centrecom.com.au/motherboards?specs=959", "MOTHERBOARD", "centrecom", "AM5");
        webScraper.scrapeAndStoreData("https://www.centrecom.com.au/motherboards?specs=1690", "MOTHERBOARD", "centrecom", "LGA1700");
        webScraper.scrapeAndStoreData("https://www.centrecom.com.au/motherboards?specs=2043", "MOTHERBOARD", "centrecom", "LGA1851");

        //POWEER SUPPLIES
        webScraper.scrapeAndStoreData("https://www.centrecom.com.au/power-supplies", "Power Supplies", "centrecom", null);



        //MSY

        //CASES

        //CPU
        msyWebScraperService.scrapeAndStoreData("https://www.msy.com.au/pc-parts/computer-parts/cpu-processors-611?page=1&mystock=1-2-3-5-6&sort=shop_price&order=ASC",
                "cpu",
                "MSY",
                null);


        //CPU COOLERS
        msyWebScraperService.scrapeAndStoreData("https://www.msy.com.au/pc-parts/computer-parts/cooling/cpu-cooling-670?page=1&mystock=1-2-3-7-4-5-6&filter_attr=0.0.0.0.130055.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0&sort=shop_price&order=ASC",
                "cpu coolers",
                "MSY",
                "AIR coolers");

        //Air cooler
        msyWebScraperService.scrapeAndStoreData("https://www.msy.com.au/pc-parts/computer-parts/cooling/cpu-cooling-670?page=1&mystock=1-2-3-7-4-5-6&filter_attr=0.0.0.0.115277.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0&sort=shop_price&order=ASC",
                "cpu coolers",
                "MSY",
                "Liquid coolers");

        return "Scraping completed!";
    }
}
