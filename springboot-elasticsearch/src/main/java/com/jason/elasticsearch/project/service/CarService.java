package com.jason.elasticsearch.project.service;

import com.jason.elasticsearch.project.model.CarModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author WongChenHoll
 * @date 2022-4-19 ζζδΊ 15:35
 **/
public interface CarService extends ElasticsearchRepository<CarModel, String> {

}
