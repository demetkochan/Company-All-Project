package com.works.repositories;

import java.util.Date;

public interface ProductJoinOrder {
    String getProductname();
    Integer getOrder_count();
    Integer getTotal();
    Date getOrder_date();
    Integer getId();
    String getCname();
    String getCemail();
    String getCphone();
    String getCsurname();
    Integer getOid();
    String getAddress();
}
