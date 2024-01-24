package Model.Inherited;
import Model.Worker.Worker;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Trader extends Worker implements Serializable {
    private static final int classID = 2000;
    private  BigDecimal CommissionRate;
    private  BigDecimal LimitCommission;


    public Trader() {
        this.CommissionRate = BigDecimal.valueOf(0);
        this.LimitCommission = BigDecimal.valueOf(0);
    }

    public Trader(String pesel, String name, String surname, String position, String salary, String phone, String comision,String limit) {
        setPesel(pesel);
        setName(name);
        setSurname(surname);
        setPosition(position);
        setSalary(salary);
        setPhoneNumber(phone);
        setCommissionRate(comision);
        setLimitCommission(limit);
    }

    public BigDecimal getCommissionRate() {
        return CommissionRate;
    }
    public BigDecimal getLimitCommission() {
        return LimitCommission;
    }
    public void setCommissionRate(String commissionRate) {
        this.CommissionRate = BigDecimal.valueOf(Double.valueOf(commissionRate));
    }
    public void setLimitCommission(String limitCommission) {
        this.LimitCommission = BigDecimal.valueOf(Double.valueOf(limitCommission));
    }

    public static ArrayList<String> getSpecificFieldName()
    {
        ArrayList<String> list = new ArrayList<>();
        list.add("CommissionRate");
        list.add("LimitCommission");
        return list;
    }

}
