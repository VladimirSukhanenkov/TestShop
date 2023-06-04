package com.example.springsecurity.repositories;

import com.example.springsecurity.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Опущен сервисный слой, который очень желательно чтобы был
@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Integer> {
    // Метод для получения корзины товаров конкретного пользователя
    List<Cart> findByPersonId(int id);

    // Удаление товара из корзины по id Товара
    // @Modifying - указываем, что будет разрешена модификация данных (т.к класс помечен
    // @Transactional, т.е. по умолчанию доступно только чтение) Удаление = модификация
    @Modifying
    @Query(value = "delete from product_cart where product_id=?1", nativeQuery = true)
    void deleteCartByProductId(int id);
}
