package com.wjx.elastics.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wjx.elastics.bean.Product;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class EsController {

    @Autowired
    Gson gson;

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @GetMapping("/megacorp/employee/{id}")
    public Map<String,Object> getEmployee(@PathVariable("id") String id){
        GetRequest getRequest = new GetRequest("megacorp","employee",id);
        Map map = new HashMap();
        GetResponse response = null;
        try {
            response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response.isExists()){
            map.put("success",true);
            map.put("data",response.getSource());
        }else{
            map.put("success",false);
        }
        return map;
    }

    @GetMapping("/order/product/{id}")
    public Map<String,Object> getOrder(@PathVariable("id") String id){
        GetRequest getRequest = new GetRequest("order","_doc",id);
        Map map = new HashMap();
        GetResponse response = null;
        try {
            response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response.isExists()){
            map.put("success",true);
            map.put("data",response.getSource());
        }else{
            map.put("success",false);
        }
        return map;
    }


    @PostMapping("/order/create")
    public Map<String,Object> createOrder(@RequestParam("buyer")String buyer,
                                          @RequestParam("date") String date,
                                          @RequestParam("totalPrice") Double total,
                                          @RequestParam("products") String products,
                                          @RequestParam("id") String id ){

        Map map = new HashMap();
        IndexRequest request = new IndexRequest("order");
        List<Product> product1ist = gson.fromJson(products,new TypeToken<List<Product>>(){}.getType());
        List<Map<String,Object>> list = product1ist.stream().map(e->{
            Map<String,Object> temp = new HashMap<>();
            Field[] fields = e.getClass().getDeclaredFields();
            for(Field f : fields){
                f.setAccessible(true);
                try {
                    temp.put(f.getName(),f.get(e));
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
            return temp;
        }).collect(Collectors.toList());

        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject().field("buyer",buyer).field("date",date)
                    .field("totalPrice",total).field("products",list).endObject();
            request.id(id).opType("create").source(builder);
            IndexResponse response = restHighLevelClient.index(request,RequestOptions.DEFAULT);
            map.put("status",response.status());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
