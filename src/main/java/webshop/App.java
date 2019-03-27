package webshop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        //test order
		String orderList = "121212345";
		
		// split into list
		String [] strArr = orderList.split("");
		
		// turn into int list
		List<Integer> numbers = new ArrayList<>(strArr.length);
		
		// add orders from split list
		for(int i = 0;i < strArr.length;i++)
		{
		   numbers.add(Integer.parseInt(strArr[i]));
		}
		
		// remove duplicates
		List<Integer> listWithoutDuplicates = numbers.stream().distinct().collect(Collectors.toList());
		
		// find Products by Id and add to list
		
		List<String> products = new ArrayList<>();
		
		for (Integer number : listWithoutDuplicates) {
			System.out.println(number);
			
			// .add
		}
		
		for (String p: products ) {
			
			// get bestelling id
			// get products id
			// get product id count from original list
			// count * product.price
			
			// insert OrderRow to db
		}
		
		
		
		
		
//		for (String temp : strArr) {
//			
//			// remove duplicate 
//			
//			
//			// make list with products
//			
//			// count id from first list
//			
//			// insert into db
//			
//			
//			System.out.println(temp);
//			
//			
//		}


    }
}
