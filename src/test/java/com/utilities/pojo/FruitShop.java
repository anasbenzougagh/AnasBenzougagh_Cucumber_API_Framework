package com.utilities.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
/*** auto generator*/
// @Data
// =
// @Getter @Setter @ToString @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FruitShop {

        private int id;
        private String name;
        private double price;
        private ArrayList<Vendor> vendors;
        @JsonProperty("image_link")
        private String imageLink;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public ArrayList<Vendor> getVendors() {
            return vendors;
        }

        public void setVendors(ArrayList<Vendor> vendors) {
            this.vendors = vendors;
        }

        public String getImageLink() {
            return imageLink;
        }

        public void setImageLink(String imageLink) {
            this.imageLink = imageLink;
        }

    @Override
    public String toString() {
        return "FruitShop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", vendors=" + vendors +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
