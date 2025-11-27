package dto;

public class ItemDTO {
    int index;
    String itemName;
    double cost;
    String sellingPrize;
    double discount;
    double profit;
    String status;

    public int getIndex() {
        return index;
    }

    public void setIndex(int id) {
        this.index = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getSellingPrize() {
        return sellingPrize;
    }

    public void setSellingPrize(String sellingPrize) {
        this.sellingPrize = sellingPrize;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
