package com.wqh.blog.domain;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @author wanqh
 * @date 2017/11/23 20:54
 * @description:
 */
public class Page<T> {

    //TODO 需要修改分页功能

    private List<T> records;// 存放分页记录T

    private int currentPageNum;// 当前页码
    private int totalPage;// 总页数

    @Value("${page.pageSize}")
    private int pageSize;// 每页显示的记录条数

    private int totalRecords;// 总记录条数
    private int startIndex;// 每页记录开始的索引

    private int prePageNum;// 上一页
    private int nextPageNum;// 下一页

    private int startPage;// 开始页码
    private int endPage;// 结束页码

    private String uri;// 查询分页的地址


    public Page(int currentPageNum, int totalRecords) {
        this.currentPageNum = currentPageNum;
        this.totalRecords = totalRecords;
        // 计算总页数
        totalPage = totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;
        // 计算每页开始记录的索引
        startIndex = (currentPageNum - 1) * pageSize;

        // 计算开始页码和结束页码：与当前页码有关

		/*
		 * 当前页码 应该显示的样子 默认 1 2 3 4 5 2 1 2 3 4 5 4 2 3 4 5 6 7 5 6 7 8 9 9 6 7 8
		 * 9 10
		 */
        if (totalPage > 5) {
            // 超出5页
            startPage = currentPageNum - 2;
            endPage = currentPageNum + 2;

            if (startPage < 1) {
                startPage = 1;
                endPage = 5;
            }

            if (endPage > totalPage) {
                endPage = totalPage;
                startPage = totalPage - 4;
            }

        } else {
            // 总共没有5页
            startPage = 1;
            endPage = totalPage;
        }

    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        // 计算总页数
        totalPage = totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;
        // 计算每页开始记录的索引
        startIndex = (currentPageNum - 1) * pageSize;
        // 计算开始页码和结束页码：与当前页码有关

		/*
		 * 当前页码 应该显示的样子 默认 1 2 3 4 5 2 1 2 3 4 5 4 2 3 4 5 6 7 5 6 7 8 9 9 6 7 8
		 * 9 10
		 */
        if (totalPage > 5) {
            // 超出5页
            startPage = currentPageNum - 2;
            endPage = currentPageNum + 2;

            if (startPage < 1) {
                startPage = 1;
                endPage = 5;
            }

            if (endPage > totalPage) {
                endPage = totalPage;
                startPage = totalPage - 4;
            }

        } else {
            // 总共没有5页
            startPage = 1;
            endPage = totalPage;
        }
        this.pageSize = pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPrePageNum() {
        prePageNum = currentPageNum - 1;
        if (prePageNum < 1){
            prePageNum = 1;
        }
        return prePageNum;
    }

    public void setPrePageNum(int prePageNum) {
        this.prePageNum = prePageNum;
    }

    public int getNextPageNum() {
        nextPageNum = currentPageNum + 1;
        if (nextPageNum > totalPage){
            nextPageNum = totalPage;
        }
        return nextPageNum;
    }

    public void setNextPageNum(int nextPageNum) {
        this.nextPageNum = nextPageNum;
    }

    @Override
    public String toString() {
        return "Page{" +
                "records=" + records +
                ", currentPageNum=" + currentPageNum +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", totalRecords=" + totalRecords +
                ", startIndex=" + startIndex +
                ", prePageNum=" + prePageNum +
                ", nextPageNum=" + nextPageNum +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", uri='" + uri + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Page p = new Page(1,23);
        System.out.println(p);
    }
}
