package ru.netology.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductRepositoryTest {

    @Test
    public void shouldAddProductSuccessfully() {
        ProductRepository repo = new ProductRepository();
        Product product = new Product(1, "Laptop", 50000);

        repo.add(product);

        Product[] expected = {product};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionWhenAddingProductWithDuplicateId() {
        ProductRepository repo = new ProductRepository();
        Product product1 = new Product(1, "Laptop", 50000);
        Product product2 = new Product(1, "Phone", 30000);

        repo.add(product1);

        Assertions.assertThrows(AlreadyExistsException.class, () -> repo.add(product2));
    }

    @Test
    public void shouldRemoveProductSuccessfully() {
        ProductRepository repo = new ProductRepository();
        Product product1 = new Product(1, "Laptop", 50000);
        Product product2 = new Product(2, "Phone", 30000);

        repo.add(product1);
        repo.add(product2);

        repo.removeById(1);

        Product[] expected = {product2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionWhenRemovingNonExistingProduct() {
        ProductRepository repo = new ProductRepository();
        Product product = new Product(1, "Laptop", 50000);
        repo.add(product);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(2);
        });
    }
}
