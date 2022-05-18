package com.jason.elasticsearch.ikMaxWord;

import com.jason.elasticsearch.commons.enums.BankEnum;
import com.jason.elasticsearch.project.model.AccountModel;
import com.jason.elasticsearch.project.service.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        model.setAcctNo("02001");
        model.setAcctName("孙七");
        model.setAcctBalance(532012.19);
        model.setOpenBank(BankEnum.TJ);
        model.setOpenDate(LocalDate.now().plusDays(-3));
        model.setRemark("孙七哈哈哈哈");

        accountRepository.save(model);

        System.out.println(model.toString());
    }

    @Test
    public void saveBatch() {

        ArrayList<AccountModel> list = new ArrayList<>();
        list.add(new AccountModel("010002", "李世民", 4289.2, BankEnum.HF, LocalDate.now().plusDays(-5), "大唐天子"));
        list.add(new AccountModel("010004", "萧炎", 289023.34, BankEnum.HF, LocalDate.now().plusDays(-1), "称霸一方"));
        list.add(new AccountModel("010005", "叶凡", 45353.8, BankEnum.HF, LocalDate.now().plusDays(-10), "叶天帝"));
        list.add(new AccountModel("010006", "萧薰儿", 29456400.1, BankEnum.HF, LocalDate.now().plusDays(-5), "古族"));
        list.add(new AccountModel("010007", "曹操", 943504580.24, BankEnum.HF, LocalDate.now().plusDays(-5), "宁让天下人负我"));
        list.add(new AccountModel("010008", "康熙", 453.04, BankEnum.HF, LocalDate.now().plusDays(-2), "战五渣"));
        Iterable<AccountModel> models = accountRepository.saveAll(list);
    }

    @Test
    public void queryAll() {
        Iterable<AccountModel> all = accountRepository.findAll(Sort.by(Sort.Order.desc("openDate")));
        for (AccountModel accountModel : all) {
            System.out.println(accountModel.toString());
        }
    }

    /**
     * 查询第一页内容，每页5条数据，并按 acct_balance 字段倒叙排列
     */
    @Test
    public void page() {
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Order.desc("acct_balance")));
        Page<AccountModel> all = accountRepository.findAll(pageRequest);
        for (AccountModel model : all) {
            System.out.println(model);
        }
    }

    @Test
    public void match() {
        List<AccountModel> list = accountRepository.findByOpenBank(BankEnum.HF);
        for (AccountModel model : list) {
            System.out.println(model);
        }
    }

    @Test
    public void matchPhrase() {
        List<AccountModel> list = accountRepository.findByRemark("补办的");
        for (AccountModel model : list) {
            System.out.println(model);
        }
    }

    @Test
    public void term() {
        List<AccountModel> term = accountRepository.queryTermByRemark("银行卡");
        for (AccountModel model : term) {
            System.out.println(model);
        }
    }

    @Test
    public void terms() {
        List<AccountModel> term = accountRepository.queryTermsByRemark("银行", "一");
        for (AccountModel model : term) {
            System.out.println(model);
        }
    }
}
