package com.shard.domain;

import java.util.List;

public class PageResult<T> {
    private List<T> items;
    private int totalCount;

    public PageResult(List<T> items, int totalCount) {
        this.items = items;
        this.totalCount = totalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public int getTotalCount() {
        return totalCount;
    }
}