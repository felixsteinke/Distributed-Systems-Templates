package com.micro.shop.cart;

import com.micro.shop.cart.access.CartItem;
import com.micro.shop.cart.internal.CartManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO convert to Stateful Bean
@RestController
@RequestMapping("")
public class CartController {

    private final CartManagement cartManagement;

    @Autowired
    public CartController(CartManagement cartManagement) {
        this.cartManagement = cartManagement;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<CartItem>> getCart() {
        return ResponseEntity.ok(cartManagement.getCart());
    }

    @PutMapping(path = "/add/{productNr}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addToCart(@PathVariable Integer productNr) {
        cartManagement.addToCart(productNr);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/checkout")
    public ResponseEntity<Void> checkoutCart() {
        cartManagement.checkoutCart();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/reset")
    public ResponseEntity<Void> resetCart() {
        cartManagement.resetCart();
        return ResponseEntity.ok().build();
    }
}
