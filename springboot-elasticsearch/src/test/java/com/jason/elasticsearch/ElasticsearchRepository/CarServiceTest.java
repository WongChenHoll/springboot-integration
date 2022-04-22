package com.jason.elasticsearch.ElasticsearchRepository;

import com.jason.elasticsearch.project.model.CarModel;
import com.jason.elasticsearch.project.service.CarService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.collapse.CollapseBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 新建一个索引：jason_car_test，专门用于操作 {@link CarModel} 类。
 *
 * @author WongChenHoll
 * @date 2022-4-19 星期二 15:38
 **/
@SpringBootTest
public class CarServiceTest {

    @Autowired
    private CarService carService;


    @Autowired
    @Qualifier(value = "restHighLevelClient")
    RestHighLevelClient client;

    @Test
    public void testSave() {
        CarModel model = new CarModel("B_0001", "Benz", "G63", "黑色", 100);
        CarModel save = carService.save(model);
        System.out.println("保存的数据：" + save);
    }

    @Test
    public void testBatchSave() {
        ArrayList<CarModel> list = new ArrayList<>();
        list.add(new CarModel("B_0002", "Benz", "S600", "黑色", 50));
        list.add(new CarModel("BMW_0001", "BMW", "740li", "棕色", 120));
        list.add(new CarModel("AUDI_0001", "AUDI", "RS7", "红色", 200));
        list.add(new CarModel("BMW_0002", "BMW", "X7", "白色", 20));
        list.add(new CarModel("AUDI_0010", "AUDI", "Q8", "黑色", 88));
        list.add(new CarModel("AUDI_0011", "AUDI", "Q7", "黑色", 66));
        list.add(new CarModel("AUDI_0021", "AUDI", "A4L", "白色", 500));

        Iterable<CarModel> all = carService.saveAll(list);
        System.out.println("批量保存的数据：" + all);
    }

    /**
     * 去重的字段必须是keyword类型的。
     */
    @Test
    public void testDistinct() throws IOException {
        SearchRequest request = new SearchRequest("jason_car_test");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.collapse(new CollapseBuilder("car_brand"));
        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {
            System.out.println("去重后的数据：" + searchHit.getSourceAsString());
        }


    }

}
