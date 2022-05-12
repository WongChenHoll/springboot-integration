package com.jason.elasticsearch.project.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jason.elasticsearch.commons.enums.BankEnum;
import org.apache.http.client.utils.DateUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * 账户信息.
 * 该文档使用了 ik_max_word 中文分词器。同时在创建数据时均使用默认的 _id 值。
 *
 * @author WongChenHoll
 * @date 2022-4-29 星期五 17:25
 **/

@JSONType(orders = {"acct_no", "acct_name", "acct_balance", "open_bank", "open_date", "remark"})
@Document(indexName = "jason_account_info")
public class AccountModel {

    @Id
    @Field(name = "acct_no", type = FieldType.Keyword)
    private String acctNo; // 账户编号

    @Field(name = "acct_name", type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String acctName; // 账户姓名

    @Field(name = "acct_balance", type = FieldType.Double)
    private double acctBalance; // 账户余额

    @Field(name = "open_bank", type = FieldType.Keyword)
    private BankEnum openBank; // 开户行

    @Field(name = "open_date", type = FieldType.Date, format = DateFormat.date)
    private Date openDate; // 开户日期

    @Field(name = "remark", type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String remark; // 账户备注


    public String toJSONString() {
        return JSON.toJSONString(this, SerializerFeature.SortField, SerializerFeature.WriteMapNullValue);
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "acctNo='" + acctNo + '\'' +
                ", acctName='" + acctName + '\'' +
                ", acctBalance=" + acctBalance +
                ", openBank=" + openBank +
                ", openDate=" + DateUtils.formatDate(openDate) +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public double getAcctBalance() {
        return acctBalance;
    }

    public void setAcctBalance(double acctBalance) {
        this.acctBalance = acctBalance;
    }

    public BankEnum getOpenBank() {
        return openBank;
    }

    public void setOpenBank(BankEnum openBank) {
        this.openBank = openBank;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
