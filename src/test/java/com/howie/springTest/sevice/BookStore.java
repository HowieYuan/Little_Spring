package com.howie.springTest.sevice;

import com.howie.springTest.dao.AccountDao;
import com.howie.springTest.dao.ItemDao;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-11-19
 * @Time 18:40
 */
public class BookStore {
    private AccountDao accountDao;
    private ItemDao itemDao;
    private String owner;

    public BookStore(AccountDao accountDao, ItemDao itemDao, String owner) {
        this.accountDao = accountDao;
        this.itemDao = itemDao;
        this.owner = owner;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public String getOwner() {
        return owner;
    }
}
