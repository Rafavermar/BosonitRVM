package com.block6pathvariableheaders.block6pathvariableheaders.Controllers;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/controlador3")
public class Controlador3 {
    @PutMapping("/post")
    public List<Map<String, Object>> post(@RequestParam("var1") Integer var1, @RequestParam("var2") Integer var2) {
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("var1", var1);
        data.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("var2", var2);
        data.add(map2);
        return data;
    }
}
