package com.max420.Scrapr_server.services.impl;

import com.max420.Scrapr_server.services.JsoupService;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;

@Service
public class JsoupServiceImpl implements JsoupService {
    @Override
    public List<String> fetchSomeData() throws IOException {
        return Jsoup.connect("https://x.com")
                .get()
                .select("span.css-1jxf684.r-bcqeeo.r-1ttztb7.r-qvutc0.r-poiln3")
                .eachText();
    }
}
