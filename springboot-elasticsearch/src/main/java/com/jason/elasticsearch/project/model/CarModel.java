package com.jason.elasticsearch.project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 注意：此类中的属性为 keyword，以便用于去重
 *
 * @author WongChenHoll
 * @date 2022-4-19 星期二 15:28
 **/
@Document(indexName = "jason_car_test")
public class CarModel {

    @Id
    private String carId; // 汽车编号

    @Field(type = FieldType.Keyword, name = "car_brand")
    private String carBrand; // 汽车品牌

    @Field(type = FieldType.Keyword, name = "car_type")
    private String carType; // 汽车型号

    @Field(type = FieldType.Keyword, name = "color")
    private String color; // 汽车颜色

    @Field(type = FieldType.Integer, name = "number")
    private int number; // 数量

    public CarModel() {
    }

    public CarModel(String carId, String carBrand, String carType, String color, int number) {
        this.carId = carId;
        this.carBrand = carBrand;
        this.carType = carType;
        this.color = color;
        this.number = number;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "carId='" + carId + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", carType='" + carType + '\'' +
                ", color='" + color + '\'' +
                ", number=" + number +
                '}';
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
