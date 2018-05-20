package com.example.andrii.myapplication.data;

import java.util.Map;

public class Pizzaria {

    private String name;

    private String description;

    private String logo;

    private Map<String, Pizza> pizzas;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Pizza> getPizzas() {
        return pizzas;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setPizzas(Map<String, Pizza> pizzas) {
        this.pizzas = pizzas;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pizzaria = {");
        sb.append("name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", logo='").append(logo).append('\'');
        sb.append(", pizzas=").append('{');
        if (pizzas == null) {
            sb.append(" pizzas is null");
        } else {
            sb.append(pizzas.toString());
        }
        sb.append('}');
        sb.append('}');
        return sb.toString();
    }
}
