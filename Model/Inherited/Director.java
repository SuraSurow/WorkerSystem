package Model.Inherited;

import Model.Worker.Worker;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Director extends Worker implements Serializable {
    private static final int classID = 1000;
    private  BigDecimal serviceAllowance;
    private String serviceCard;
    private BigDecimal limitCosts;

    public Director() {
        this.serviceAllowance = BigDecimal.valueOf(0);
        this.serviceCard = String.valueOf(0);
        this.limitCosts = BigDecimal.valueOf(0);
    }
    public BigDecimal getServiceAllowance() {
        return serviceAllowance;
    }

    public String getServiceCard() {
        return serviceCard;
    }

    public void setServiceCard(String serviceCard) {
        this.serviceCard = serviceCard;
    }

    public BigDecimal getLimitCosts() {
        return limitCosts;
    }

    public void setLimitCosts(String limitCosts) {
        this.limitCosts = BigDecimal.valueOf(Double.valueOf(limitCosts));
    }

    public void  setServiceAllowance ( String amount)
    {
        this.serviceAllowance =  BigDecimal.valueOf(Double.valueOf(amount));
    }

    public static ArrayList<String> getSpecificFieldName()
    {
        ArrayList<String> list = new ArrayList<>();
        list.add("ServiceAllowance");
        list.add("ServiceCard");
        list.add("LimitCosts");
        return list;
    }


}
