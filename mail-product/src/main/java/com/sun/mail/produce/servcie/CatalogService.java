package com.sun.mail.produce.servcie;

import com.sun.mail.produce.vo.Catalog2Vo;

import java.util.List;
import java.util.Map;

public interface CatalogService {


    Map<String, List<Catalog2Vo>> getCatalogJson();
}
