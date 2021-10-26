package com.semihbkgr.ecommerce.modelcommon.auditable;

public interface TimeAuditable {

    void setCreatedAt(long at);

    long getCreatedAt();

    void setUpdatedAt(long at);

    long getUpdatedAt();

}
