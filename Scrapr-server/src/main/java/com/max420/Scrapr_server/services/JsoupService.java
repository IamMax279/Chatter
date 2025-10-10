package com.max420.Scrapr_server.services;

import java.io.IOException;
import java.util.List;

public interface JsoupService {
    List<String> fetchSomeData() throws IOException;
}
