package restAssuredTests.simpleAPITest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class UsersDetails {

	private Integer page;
	private Integer per_page;
	private Integer total;
	private Integer total_pages;
	private List<Datum> data = null;
	private Support support;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getper_page() {
		return per_page;
	}

	public void setPerPage(Integer per_page) {
		this.per_page = per_page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer gettotal_pages() {
		return total_pages;
	}

	public void settotal_pages(Integer total_pages) {
		this.total_pages = total_pages;
	}

	public List<Datum> getData() {
		return data;
	}

	public void setData(List<Datum> data) {
		this.data = data;
	}

	public Support getSupport() {
		return support;
	}

	public void setSupport(Support support) {
		this.support = support;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(UsersDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
				.append('[');
		sb.append("page");
		sb.append('=');
		sb.append(((this.page == null) ? "<null>" : this.page));
		sb.append(',');
		sb.append("perPage");
		sb.append('=');
		sb.append(((this.per_page == null) ? "<null>" : this.per_page));
		sb.append(',');
		sb.append("total");
		sb.append('=');
		sb.append(((this.total == null) ? "<null>" : this.total));
		sb.append(',');
		sb.append("total_pages");
		sb.append('=');
		sb.append(((this.total_pages == null) ? "<null>" : this.total_pages));
		sb.append(',');
		sb.append("data");
		sb.append('=');
		sb.append(((this.data == null) ? "<null>" : this.data));
		sb.append(',');
		sb.append("support");
		sb.append('=');
		sb.append(((this.support == null) ? "<null>" : this.support));
		sb.append(',');
		if (sb.charAt((sb.length() - 1)) == ',') {
			sb.setCharAt((sb.length() - 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}

	
}
