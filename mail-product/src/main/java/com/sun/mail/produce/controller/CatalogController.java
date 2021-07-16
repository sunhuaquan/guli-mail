package com.sun.mail.produce.controller;


import com.sun.mail.produce.servcie.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("catalog")
public class CatalogController {


    @Autowired
    private CatalogService catalogService;

    @RequestMapping("/getCatalogJson")
    public Map getCatalogJson() {

        return catalogService.getCatalogJson();

    }
}
