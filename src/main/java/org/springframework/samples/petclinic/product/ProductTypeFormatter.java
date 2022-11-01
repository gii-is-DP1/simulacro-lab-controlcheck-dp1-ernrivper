package org.springframework.samples.petclinic.product;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeFormatter implements Formatter<ProductType>{

    private ProductService serve;
	
	@Autowired
	public ProductTypeFormatter(ProductService productService) {
		this.serve=productService;
	}
    
    @Override
    public String print(ProductType object, Locale locale) {
        return object.getName();
    }

    @Override
    public ProductType parse(String text, Locale locale) throws ParseException {
        List<ProductType> productTypes=serve.findAllProductTypes();
    	for (ProductType p:productTypes) {
			if (p.getName().equals(text)) {
				return p;
			}
		} throw new ParseException("ProductType not found"+ text, 0);

    }
    
}
