package model;

public class OrderRow {
	
	private int id;
	private Order order;
	private Product product;
	private int count;
	private float price;
	
	
	public OrderRow(int id, Order order, Product product, int count, float price) {
		
		this.id = id;
		this.order = order;
		this.product = product;
		this.count = count;
		this.price = price;
	}
	
	public OrderRow(Order order, Product product, int count, float price) {
		
		this.order = order;
		this.product = product;
		this.count = count;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object anderObject) {
		boolean isEqual = false;

		if (anderObject instanceof OrderRow) {
			OrderRow andereOrderRow = (OrderRow) anderObject;

			if (this.id == andereOrderRow.getId() &&
					this.order.equals(andereOrderRow.getOrder()) &&
					this.product.equals(andereOrderRow.getProduct()) &&
					this.count == andereOrderRow.getCount() &&
					this.price == andereOrderRow.getPrice()) {
				isEqual = true;
			}
		}
		return isEqual;
	}
	
}
