package com.sun.mail.produce.servcie.impl;

import com.sun.mail.produce.entity.SkuImagesEntity;
import com.sun.mail.produce.entity.SkuInfoEntity;
import com.sun.mail.produce.servcie.SkuInfoService;
import com.sun.mail.produce.vo.SkuItemSaleAttrVo;
import com.sun.mail.produce.vo.SkuItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class SkuInfoServiceImpl implements SkuInfoService {


    @Autowired
    private ThreadPoolExecutor executor;

    @Override
    public SkuItemVo item(Long skuId) throws Exception {

        SkuItemVo skuItemVo = new SkuItemVo();

        //supplyAsync可以支持返回值
        CompletableFuture<SkuInfoEntity> infoFuture = CompletableFuture.supplyAsync(() -> {
            //1、sku基本信息的获取  pms_sku_info
            SkuInfoEntity info = this.getById(skuId);
            skuItemVo.setInfo(info);
            return info;
        }, executor);
        CompletableFuture<Void> voidCompletableFuture = infoFuture.thenAcceptAsync(skuInfoEntity -> {
            List<SkuItemSaleAttrVo> spuList = this.getSpuList(skuInfoEntity.getSkuId());
            skuItemVo.setSaleAttr(spuList);

        },executor);

        CompletableFuture<Void> imageFuture = CompletableFuture.runAsync(() -> {
            List<SkuImagesEntity> imagesEntities = this.getImagesBySkuId(skuId);
            skuItemVo.setImages(imagesEntities);
        }, executor);

        CompletableFuture.allOf(imageFuture).get();

        return skuItemVo;
    }

    private List<SkuImagesEntity> getImagesBySkuId(Long skuId) {
        System.out.println("------------------->"+Thread.currentThread().getName()+":getImagesBySkuId");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<SkuImagesEntity> list=new ArrayList<>();
        SkuImagesEntity skuImagesEntity=new SkuImagesEntity();
        skuImagesEntity.setUrl("http://www.baidu.com");
        list.add(skuImagesEntity);
        return list;
    }

    private List<SkuItemSaleAttrVo> getSpuList(Long skuId) {
        System.out.println("------------------->"+Thread.currentThread().getName()+":getSpuList");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<SkuItemSaleAttrVo> attrVos=new ArrayList<>();
        SkuItemSaleAttrVo skuItemSaleAttrVo=new SkuItemSaleAttrVo();
        skuItemSaleAttrVo.setAttrId(1001L);
        skuItemSaleAttrVo.setAttrName("手机");
        attrVos.add(skuItemSaleAttrVo);
        return attrVos;
    }

    @Override
    public SkuInfoEntity getById(Long skuId) {
        System.out.println("------------------->"+Thread.currentThread().getName()+":getById");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SkuInfoEntity skuInfoEntity=new SkuInfoEntity();
        skuInfoEntity.setSkuId(1000L);

        return skuInfoEntity;
    }
}
