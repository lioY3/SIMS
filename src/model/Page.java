package model;

/**
 * 封装分页参数
 */
public class Page {

	private int start; // 开始查询位置
	private int code; // 页码
	private int size;// 每页记录数

	public Page() {

	}

	public Page(int code, int size) {
		this.code = code;
		this.size = size;
		this.start = (code - 1) * size;
	}

	public int getStart() {
		this.start = (code - 1) * size;
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
