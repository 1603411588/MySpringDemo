package com.liuyi.entity;

import java.util.List;

public class PageQueryResult<T> {

	private List<T> rows;
	private long total;

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}
