package cloud.truesun.domain;

import java.util.List;

public class PageResult {
    // 当前页码值
    private long current;
    // 每页显示数
    private long size;
    // 一共多少页
    private long Pages;
    //一共多少条数据
    private long total;
    // 数据
    private List<House> records;

    public PageResult(long current, long size, long getPages, long total, List<House> records) {
        this.current = current;
        this.size = size;
        this.Pages = getPages;
        this.total = total;
        this.records = records;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getPages() {
        return Pages;
    }

    public void setPages(long getPages) {
        this.Pages = getPages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<House> getRecords() {
        return records;
    }

    public void setRecords(List<House> records) {
        this.records = records;
    }
}
