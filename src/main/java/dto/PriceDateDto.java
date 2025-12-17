package dto;

public class PriceDateDto {
    private  String manufactureDate;
    private  String expireDate;
    private  String price;

    public PriceDateDto(String manufactureDate, String expireDate, String price) {
        this.manufactureDate = manufactureDate;
        this.expireDate = expireDate;
        this.price = price;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
