package com.jason.elasticsearch.commons.enums;

/**
 * @author WongChenHoll
 * @date 2022-4-29 17:40
 **/
public enum BankEnum {

    BJ("BeiJing", "北京市分行", "001"),
    SH("ShangHai", "上海市分行", "002"),
    GZ("GuangZhou", "广州市分行", "003"),
    SZ("ShenZhen", "深圳市分行", "004"),
    CD("ChengDu", "成都市分行", "005"),
    HZ("HangZhou", "杭州市分行", "006"),
    HF("HeFei", "合肥市分行", "007"),
    TJ("TianJin", "天津市分行", "008");

    private final String fullNameEn; // 英语全称
    private final String fullNameCn; // 英中文全称
    private final String number; // 编号

    BankEnum(String fullNameEn, String fullNameCn, String number) {
        this.fullNameEn = fullNameEn;
        this.fullNameCn = fullNameCn;
        this.number = number;
    }

}
