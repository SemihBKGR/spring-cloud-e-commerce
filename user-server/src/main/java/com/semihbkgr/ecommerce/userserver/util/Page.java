package com.semihbkgr.ecommerce.userserver.util;

import lombok.Data;

@Data(staticConstructor = "of")
public class Page {

    public final int pageCount;
    public final int pageSize;

    private Page(int pageCount, int pageSize) {
        if (pageCount < 0)
            throw new IllegalArgumentException("PageCount cannot be negative value");
        if (pageSize < 1)
            throw new IllegalArgumentException("PageSize must be positive value");
        this.pageCount = pageCount;
        this.pageSize = pageSize;
    }

    public Page next() {
        return new Page(pageCount + 1, pageSize);
    }

    public Page previous() {
        return new Page(pageCount - 1, pageSize);
    }

    public int startIndex() {
        return pageCount * pageSize;
    }

    public int lastIndex() {
        return (pageCount + 1) * pageSize;
    }

}
