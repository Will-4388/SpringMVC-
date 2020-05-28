package top.undefinded.domain;

/**
 * @author will
 * @date 2020/5/22
 */
public class Account {
    private Double number;
    private String accountName;

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", accountName='" + accountName + '\'' +
                '}';
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
