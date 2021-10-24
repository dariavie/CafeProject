package by.training.cafeproject.domain;

import com.sun.tools.corba.se.idl.constExpr.Or;

public enum OrderStatus {
    INPROGRESS("выполняется"),
    DONE("завершен");

    private String name;

    private OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return ordinal()+1;
    }

    public static OrderStatus getById(Integer id) {
        return OrderStatus.values()[id-1];
    }
}
