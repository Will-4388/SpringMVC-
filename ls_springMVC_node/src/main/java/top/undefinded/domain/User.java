package top.undefinded.domain;

import java.util.List;
import java.util.Map;

/**
 * @author will
 * @date 2020/5/22
 */
public class User {
    private String id;
    private String password;
    private List<String> list;
    private Map<String, String> map;
    private Account money;
    private List<Account> list_account;
    private Map<String, Account> map_account;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", list=" + list +
                ", map=" + map +
                ", money=" + money +
                ", list_account=" + list_account +
                ", map_account=" + map_account +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Account getMoney() {
        return money;
    }

    public void setMoney(Account money) {
        this.money = money;
    }

    public List<Account> getList_account() {
        return list_account;
    }

    public void setList_account(List<Account> list_account) {
        this.list_account = list_account;
    }

    public Map<String, Account> getMap_account() {
        return map_account;
    }

    public void setMap_account(Map<String, Account> map_account) {
        this.map_account = map_account;
    }
}
