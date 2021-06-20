package kodlamaio.northwind.dataAccess.abstracts;

import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dto.ProductWithCategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductDao extends JpaRepository<Product,Integer> {

    Product getByProductName(String productName);

    Product getByProductNameAndCategory_CategoryId(String productName,int categoryId);

    List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId);

    List<Product> getByCategoryIn(List<Integer> categories);

    List<Product> getByProductNameContains(String productName);

    List<Product> getByProductNameStartsWith(String productName);

    @Query("From Product p where p.productName=:productName and p.category.categoryId=:categoryId")
    List<Product> getByNameAndCategory( String productName,int categoryId);

    @Query("select new kodlamaio.northwind.entities.dto.ProductWithCategoryDto" +
            "(p.id,p.productName,c.categoryName) " +
            "From Category c inner JOIN  c.products p")
    List<ProductWithCategoryDto> getProductWithCategoryDetails();

    //select p.productId,p.productName,c.categoryName
    // from Category c inner join Product p
    // on c.categoryId=p.categoryId;

}
