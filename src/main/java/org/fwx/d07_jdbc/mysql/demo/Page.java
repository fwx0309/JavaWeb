package org.fwx.d07_jdbc.mysql.demo;

import java.util.List;

public class Page<T> {

	private List<T> list; // 每页查到的记录存放的集合
	public static final int PAGE_SIZE = 4; // 每页显示的记录数
	private int pageNo; // 当前页
	//	private int totalPageNo; // 总页数，通过计算得到
	private int totalRecord; // 总记录数，通过查询数据库得到

	public Page() {
	}

	public Page(List<T> list, int pageNo, int totalRecord) {
		this.list = list;
		this.pageNo = pageNo;
		this.totalRecord = totalRecord;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	@Override
	public String toString() {
		return "Page{" +
				"list=" + list +
				", pageNo=" + pageNo +
				", totalRecord=" + totalRecord +
				'}';
	}
}