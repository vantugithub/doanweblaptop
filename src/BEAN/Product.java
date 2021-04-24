package BEAN;

public class Product extends Abs<Product>{
	public Product() {
		super();
	}
	private String name;
	private String description;
	private String bigDescription;
	private String content;
	private Long price;
	private Long promotionPrice;
	private String image;
	private String imageList;
	private String title;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(Long promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImageList() {
		return imageList;
	}
	public void setImageList(String imageList) {
		this.imageList = imageList;
	}
	public int getWarranty() {
		return warranty;
	}
	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getBigDescription() {
		return bigDescription;
	}
	public void setBigDescription(String bigDescription) {
		this.bigDescription = bigDescription;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	private int warranty;
	private boolean status;
	
}
