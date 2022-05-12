package com.jason.elasticsearch.ikMaxWord;

import com.jason.elasticsearch.commons.enums.BankEnum;
import com.jason.elasticsearch.project.model.AccountModel;
import com.jason.elasticsearch.project.service.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * 通过Spring中的ElasticsearchRepository接口测试中文分词器 ik_max_word
 *
 * @author WongChenHoll
 * @date 2022-5-7 星期六 17:22
 **/
@SpringBootTest
public class TestAccountRepository {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void saveTest() {
        AccountModel model = new AccountModel();
        model.setAcctNo("010003");
        model.setAcctName("王五");
        model.setAcctBalance(28984322.02);
        model.setOpenBank(BankEnum.CD);
        model.setOpenDate(new Date());
        model.setRemark("王五的第一张银行卡");

        accountRepository.save(model);
    }
}
