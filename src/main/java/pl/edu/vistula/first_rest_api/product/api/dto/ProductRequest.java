package pl.edu.vistula.first_rest_api.product.api.dto;

public class ProductRequest {

    private String name;

    public ProductRequest() {
    }

    public ProductRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

