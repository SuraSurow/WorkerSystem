package MODEL.Inherited;
import MODEL.Worker.Worker;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Trader extends Worker {
    public Trader() {
        CommissionRate = BigDecimal.valueOf(0);
        LimitCommission = BigDecimal.valueOf(0);
    }

    private BigDecimal CommissionRate;
    private BigDecimal LimitCommission;
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
