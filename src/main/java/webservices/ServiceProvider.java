package webservices;

public class ServiceProvider {
	private static ProductService productService = new ProductService();
	private static SaleService saleService = new SaleService();

	public static ProductService getProductService() {
		return productService;
	}
	
	public static SaleService getSaleService() {
		return saleService;
	}
}
