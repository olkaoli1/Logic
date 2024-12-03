package ru.netology.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AlreadyExistsExceptionTest {

    @Test
    public void shouldThrowAlreadyExistsExceptionWhenProductWithDuplicateIdIsAdded() {
        // Создаем репозиторий и товары
        ProductRepository repo = new ProductRepository();
        Product product1 = new Product(1, "Laptop", 50000);
        Product product2 = new Product(1, "Phone", 30000);  // Этот товар будет с таким же ID

        repo.add(product1);

        // Проверяем, что выбрасывается исключение при попытке добавить товар с уже существующим ID
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product2);
        });
    }
}
