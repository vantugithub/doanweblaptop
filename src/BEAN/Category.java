package BEAN;

public class Category extends Abs<Category>{
	public Category() {
		super();
	}
	private String name;
	private String siteTitle;
	private int parentId;
	private boolean visibility;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSiteTitle() {
		return siteTitle;
	}
	public void setSiteTitle(String siteTitle) {
		this.siteTitle = siteTitle;
	}
	public int getParentId() {
		return parentId;
	}
	public Category(String name, String siteTitle, int parentId, boolean visibility) {
		super();
		this.name = name;
		this.siteTitle = siteTitle;
		this.parentId = parentId;
		this.visibility = visibility;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public boolean isVisibility() {
		return visibility;
	}
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

}
