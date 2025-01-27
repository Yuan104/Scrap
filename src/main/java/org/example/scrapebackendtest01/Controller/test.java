package org.example.scrapebackendtest01.Controller;
import org.example.scrapebackendtest01.Scraper.MsyWebScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class test {
    @Autowired
    private MsyWebScraperService msyWebScraperService;

    @GetMapping("/start")
    public String startScraper() {
        msyWebScraperService.scrapeAndStoreData("https://www.centrecom.com.au/cpu-processors?specs=1686", "cpu", "centrecom", "AM5");
        return "Scraping completed!";
    }
}

//https://www.msy.com.au/pc-parts/computer-parts/cases-139?page=1&mystock=1-2-3-5&filter_attr=25716-60705-112420-25741-25667.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0&sort=shop_price&order=ASC
//https://www.msy.com.au/pc-parts/computer-parts/cases-139?page=2&mystock=1-2-3-5&filter_attr=25716-60705-112420-25741-25667.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0&sort=shop_price&order=ASC
//https://www.msy.com.au/pc-parts/computer-parts/cases-139?page=2&mystock=1-2-3-5&filter_attr=25716-60705-112420-25741-25667.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0&sort=shop_price&order=ASC
//https://www.msy.com.au/pc-parts/computer-parts/cases-139?page=1&mystock=1-2-3-5&filter_attr=25716-60705-112420-25741-25667.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0&sort=shop_price&order=ASC
//https://www.msy.com.au/pc-parts/computer-parts/cases-139?mystock=1-2-3-5&filter_attr=25716-60705-112420-25741-25667.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0
//https://www.msy.com.au/pc-parts/computer-parts/cases-139?page=2&mystock=1-2-3-5&filter_attr=25716-60705-112420-25741-25667.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0&sort=shop_price&order=ASC
//https://www.msy.com.au/pc-parts/computer-parts/cases-139?page=2&mystock=1-2-3-5&filter_attr=25716-60705-112420-25741-25667.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0&sort=shop_price&order=ASC
//https://www.msy.com.au/pc-parts/computer-parts/cases-139?page=2&mystock=1-2-3-5-6-7-4&filter_attr=25716-60705-112420-25741-25667.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0&sort=shop_price&order=ASC
//https://www.msy.com.au/pc-parts/computer-parts/cases-139?page=3&mystock=1-2-3-5-6-7-4&filter_attr=25716-60705-112420-25741-25667.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0&sort=shop_price&order=ASC
//https://www.msy.com.au/pc-parts/computer-parts/cases-139?mystock=1-2-3-5-6-7-4&filter_attr=25716-60705-112420-25741-25667.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0

//https://www.msy.com.au/pc-parts/computer-parts/cases-139?page=3&mystock=1-2-3-5&sort=shop_price&order=ASC
//https://www.msy.com.au/pc-parts/computer-parts/cases-139?page=3&mystock=1-2-3-5&sort=shop_price&order=ASC
//https://www.msy.com.au/pc-parts/computer-parts/cases-139?page=3&mystock=1-2-3-5&sort=shop_price&order=ASC


//https://www.msy.com.au/pc-parts/computer-parts/cases-139?page=1&mystock=1-2-3-5&filter_attr=25716-60705-112420-25741-25667.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0&sort=shop_price&order=ASC
//https://www.msy.com.au/pc-parts/computer-parts/cases-139?page=2&mystock=1-2-3-5&filter_attr=25716-60705-112420-25741-25667.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0&sort=shop_price&order=ASC
