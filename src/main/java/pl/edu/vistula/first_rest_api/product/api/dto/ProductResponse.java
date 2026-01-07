package pl.edu.vistula.first_rest_api.product.api.dto;

public class ProductResponse {

    private Long id;
    private String name;

    public ProductResponse() {
    }

    public ProductResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

