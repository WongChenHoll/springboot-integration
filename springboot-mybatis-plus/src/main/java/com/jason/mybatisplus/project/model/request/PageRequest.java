package com.jason.mybatisplus.project.model.request;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author WongChenHoll
 * @date 2022-4-11 21:52
 **/
public class PageRequest<T> {

    private T data;
    private Page<T> page;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }
}
