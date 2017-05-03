package com.bawei.yuekaolianxia.bean;

/**
 * 类用途:
 * 作者:崔涵淞
 * 时间: 2017/5/3 8:08.
 */

public class JsonBean {
    private int id;
    private String loupan_name;
    private String pic;
    private String region_title;
    private int price;
    private String new_price_back;
    private String address;
    private String sale_title;

    public JsonBean(String loupan_name, String pic, String region_title, int price, String new_price_back, String address, String sale_title) {
        this.loupan_name = loupan_name;
        this.pic = pic;
        this.region_title = region_title;
        this.price = price;
        this.new_price_back = new_price_back;
        this.address = address;
        this.sale_title = sale_title;
    }

    @Override
    public String toString() {
        return "JsonBean{" +
                "id=" + id +
                ", loupan_name='" + loupan_name + '\'' +
                ", pic='" + pic + '\'' +
                ", region_title='" + region_title + '\'' +
                ", price='" + price + '\'' +
                ", new_price_back='" + new_price_back + '\'' +
                ", address='" + address + '\'' +
                ", sale_title='" + sale_title + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoupan_name() {
        return loupan_name;
    }

    public void setLoupan_name(String loupan_name) {
        this.loupan_name = loupan_name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getRegion_title() {
        return region_title;
    }

    public void setRegion_title(String region_title) {
        this.region_title = region_title;
    }

    public int getNew_price_value() {
        return price;
    }

    public void setNew_price_value(int price) {
        this.price = price;
    }

    public String getNew_price_back() {
        return new_price_back;
    }

    public void setNew_price_back(String new_price_back) {
        this.new_price_back = new_price_back;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSale_title() {
        return sale_title;
    }

    public void setSale_title(String sale_title) {
        this.sale_title = sale_title;
    }
}
