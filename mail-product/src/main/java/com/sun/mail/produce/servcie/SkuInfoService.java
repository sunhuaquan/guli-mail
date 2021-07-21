package com.sun.mail.produce.servcie;

import com.sun.mail.produce.entity.SkuInfoEntity;
import com.sun.mail.produce.vo.SkuItemVo;

import java.util.concurrent.ExecutionException;

public interface SkuInfoService {



    SkuItemVo item(Long skuId) throws Exception;

    SkuInfoEntity getById(Long skuId);
}
