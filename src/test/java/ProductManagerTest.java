package ru.netology.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class ProductManagerTest {

    @Test
    public void shouldAddProduct() {
        ProductRepository repository = mock(ProductRepository.class); // Мок репозитория
        ProductManager manager = new ProductManager(repository);
        Product product = new Product(1, "Apple", 100);

        manager.add(product);

        // Проверяем, что метод add в репозитории был вызван
        verify(repository).add(product);
    }

    @Test
    public void shouldSearchByName() {
        ProductRepository repository = mock(ProductRepository.class); // Мок репозитория
        ProductManager manager = new ProductManager(repository);

        Product product1 = new Product(1, "Apple", 100);
        Product product2 = new Product(2, "Banana", 50);

        // Имитируем, что репозиторий возвращает массив из двух товаров
        when(repository.findAll()).thenReturn(new Product[]{product1, product2});

        Product[] expected = {product1};
        Product[] actual = manager.searchBy("Apple");

        // Проверяем, что поиск возвращает корректный результат
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyWhenNoProductsMatchSearch() {
        ProductRepository repository = mock(ProductRepository.class);
        ProductManager manager = new ProductManager(repository);

        Product product1 = new Product(1, "Apple", 100);
        Product product2 = new Product(2, "Banana", 50);

        // Имитируем товары в репозитории
        when(repository.findAll()).thenReturn(new Product[]{product1, product2});

        // Поиск, который ничего не найдет
        Product[] expected = {};
        Product[] actual = manager.searchBy("Orange");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowAlreadyExistsExceptionWhenAddingDuplicateProduct() {
        ProductRepository repository = mock(ProductRepository.class);
        ProductManager manager = new ProductManager(repository);

        Product product = new Product(1, "Apple", 100);

        // Имитируем, что такой товар уже существует
        doThrow(new AlreadyExistsException("Product with ID 1 already exists"))
                .when(repository).add(product);

        // Проверяем, что при добавлении выбрасывается исключение
        Assertions.assertThrows(AlreadyExistsException.class, () -> manager.add(product));
    }
}
