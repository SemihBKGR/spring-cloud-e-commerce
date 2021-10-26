package com.semihbkgr.ecommerce.modelcommon.auditable;

public interface PersonAuditable {

    void setCreatedBy(String by);

    String getCreatedBy();

    void setUpdatedBy(String by);

    String getUpdatedBy();

}
