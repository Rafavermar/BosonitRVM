package com.block6pathvariableheaders.block6pathvariableheaders.Controllers;

import com.block6pathvariableheaders.block6pathvariableheaders.Repository.AllData;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/controlador5")
public class Controlador5 {
    @PostMapping("/all")
    public AllData all(@RequestBody(required = false) String body,
                       @RequestHeader Map<String, String> headers,
                       @RequestParam Map<String, String> requestParams) {
        AllData allData = new AllData();
        allData.setBody(body);
        allData.setHeaders(new ArrayList<>(headers.values()));
        allData.setRequestParams(new ArrayList<>(requestParams.values()));
        return allData;
    }
}
