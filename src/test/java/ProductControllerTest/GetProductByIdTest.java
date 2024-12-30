package ProductControllerTest;


import com.example.demo.SpringProjectApplication;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import com.example.demo.product.repository.ProductRepository;
import com.example.demo.product.service.GetProductById;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SpringProjectApplication.class)
public class GetProductByIdTest {

    @InjectMocks
    private GetProductById getProductById;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void getProductById_validId_returnsProductDTO(){

        //Given
        Product product = new Product();
        product.setId(1);
        product.setPrice(9.99);
        product.setName("Best Chocolate");
        product.setDescription("Silky Dark");
        product.setQuantity(10);

        ProductDTO expectedDTO = new ProductDTO(product);

        //Given
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        //When / Then
        ResponseEntity<ProductDTO> actualResponse = getProductById.execute(product.getId());

        //Then
        assertEquals(expectedDTO , actualResponse.getBody());
        assertEquals(HttpStatus.OK , actualResponse.getStatusCode());
    }

    @Test
    public void getProductById_invalidId_throwsProductNotFoundException(){
        //Given
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        //When /Then
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class , () -> getProductById.execute(1));

        //Then
        assertEquals("Product not Found" , exception.getSimpleResponse().getMessage());
    }
}
