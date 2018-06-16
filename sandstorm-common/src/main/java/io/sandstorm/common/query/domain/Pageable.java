package io.sandstorm.common.query.domain;

import io.sandstorm.common.ValueChecks;
import io.sandstorm.common.InputAssert;

public abstract class Pageable {

    /**
     * The number of items per page
     */
    protected Integer pageSize;

    /**
     * The serial number of the current page, and the serial number starts from 1
     */
    protected Integer pageNo;

    private Sort sort;

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public int pageSize() {
        return pageSize;
    }

    public int pageNo() {
        return pageNo;
    }

    public int offset() {
        return (pageNo - 1) * pageSize;
    }

    public Sort sort() {
        return sort;
    }

    public Pageable asPageable() {
        return this;
    }

    public void validate() {
        InputAssert.assertTrue(
                ValueChecks.notNull(pageSize) && ValueChecks.gte(pageSize, 1),
                "pageSize is required and must be gte 1");
        InputAssert.assertTrue(
                ValueChecks.notNull(pageNo) && ValueChecks.gte(pageNo, 1),
                "pageNo is required and must be gte 1");
    }
}