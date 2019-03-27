package webservices;

public class ServiceProvider {
	private static ProductService productService = new ProductService();
	private static SaleService saleService = new SaleService();
	private static OrderService orderService = new OrderService();
	private static OrderRowService orderRowService = new OrderRowService();

	public static ProductService getProductService() {
		return productService;
	}
	
	public static SaleService getSaleService() {
		return saleService;
	}
	
	public static OrderService getOrderService() {
		return orderService;
	}
	
	public static OrderRowService getOrderRowService() {
		return orderRowService;
	}
}
