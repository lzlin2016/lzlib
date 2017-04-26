package com.lib.lzlin.api.model;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 临时数据 Bean
 * 创建人: lz - Administrator
 * 创建时间:  2017/4/24 18:08
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class TempBean {
    int position;
    String title;
    String content;

    public TempBean() {
    }

    public TempBean(int position, String title, String content) {
        this.position = position;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "TempBean{" +
                "position=" + position +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
