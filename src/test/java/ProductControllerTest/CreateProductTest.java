package ProductControllerTest;

import com.example.demo.SpringProjectApplication;
import com.example.demo.exceptions.ProductNotValidException;
import com.example.demo.product.model.Product;
import com.example.demo.product.repository.ProductRepository;
import com.example.demo.product.service.CreateProduct;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = SpringProjectApplication.class)
public class CreateProductTest {

    @InjectMocks
    private CreateProduct createProduct;

    @Mock
    private ProductRepository productRepository;

    //MethodName_StateUnderTest_ExpectedBehavior
    @Test
    public void createProduct_validProduct_returnSuccess(){

        // Given, When , Then
        // Arrange, Act, Assert

        //Given
        //Arrange
        Product product = new Product();
        product.setId(1);
        product.setPrice(9.99);
        product.setName("Best Chocolate");
        product.setDescription("Silky Dark");
        product.setQuantity(10);

        //When
        //Act
        ResponseEntity response = createProduct.execute(product);

        //Then
        //Assert
        assertEquals(HttpStatus.OK , response.getStatusCode());
    }

    @Test
    public void createProduct_invalidPrice_throwsInvalidPriceException(){
        //Given
        Product product = new Product();
        product.setId(1);
        product.setPrice(0.0);
        product.setName("Best Chocolate");
        product.setDescription("Silky Dark");
        product.setQuantity(10);

        //When/ Then
        ProductNotValidException exception = assertThrows(ProductNotValidException.class, () -> createProduct.execute(product));

        //Then 2
        assertEquals("Product price cannot be less than 0" , exception.getSimpleResponse().getMessage());
    }
}
