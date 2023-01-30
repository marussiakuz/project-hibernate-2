package com.javarush;

import com.javarush.entity.*;
import com.javarush.entity.enums.Rating;
import com.javarush.entity.enums.SpecialFeature;
import com.javarush.service.*;

import java.math.BigDecimal;
import java.time.Year;
import java.util.*;

public class Demo {
    private static final CustomerService CUSTOMER_SERVICE = new CustomerService();
    private static final RentalService RENTAL_SERVICE = new RentalService();
    private static final InventoryService INVENTORY_SERVICE = new InventoryService();
    private static final StaffService STAFF_SERVICE = new StaffService();
    private static final CategoryService CATEGORY_SERVICE = new CategoryService();
    private static final LanguageService LANGUAGE_SERVICE = new LanguageService();
    private static final StoreService STORE_SERVICE = new StoreService();
    private static final PaymentService PAYMENT_SERVICE = new PaymentService();

    public static void main(String[] args) {

        // case 1: save new customer

        Customer newCustomer = createCustomer();
        short cityId = 34;
        byte storeId = 2;

        Customer savedCustomer = CUSTOMER_SERVICE.saveNewCustomer(newCustomer, cityId, storeId);

        // case 2: customer returns film

        Rental notReturned = RENTAL_SERVICE.getAnyRentalNotReturnedByStoreId(storeId);

        RENTAL_SERVICE.returnFilm(notReturned.getCustomer().getId(), notReturned.getInventory().getFilm().getId(),
                storeId);

        // case 3: customer went to the store and rented inventory there, made a payment from the staff

        Inventory availableInventory = INVENTORY_SERVICE.findAnyAvailableInventoryByStoreId((byte) 1);
        Staff manager = STAFF_SERVICE.getManagerByStoreId((byte) 1);
        Payment payment = createPayment(savedCustomer, availableInventory, manager);

        PAYMENT_SERVICE.savePayment(payment);

        // case 4: new film was made, and it became available for rent

        Film film = createFilm();
        Inventory inventory = createInventory(film, STORE_SERVICE.getStoreById(storeId));

        INVENTORY_SERVICE.addInventory(inventory);
    }

    private static Address createAddress() {
        return Address.builder()
                .line("2608 S Saunders St")
                .district("NC")
                .phone("+1 919-706-6724")
                .build();
    }

    private static Customer createCustomer() {
        return Customer.builder()
                .email("mk@gmail.com")
                .firstName("Marik")
                .lastName("Koterskii")
                .active(true)
                .address(createAddress())
                .build();
    }

    private static Payment createPayment(Customer customer, Inventory inventory, Staff manager) {
        Rental rental = createRental(customer, manager, inventory);

        return Payment.builder()
                .customer(customer)
                .staff(manager)
                .amount(BigDecimal.valueOf(0.99))
                .rental(rental)
                .build();
    }

    private static Rental createRental(Customer customer, Staff manager, Inventory inventory) {
        return Rental.builder()
                .staff(manager)
                .customer(customer)
                .inventory(inventory)
                .build();
    }

    private static Film createFilm() {
        final short length = 900;
        final byte rentalDuration = 60;

        return Film.builder()
                .title("New")
                .categories(new HashSet<>(CATEGORY_SERVICE.findAllCategoriesWithNames("drama", "comedy", "action")))
                .actors(createActors())
                .language(LANGUAGE_SERVICE.getLanguageByName("german"))
                .length(length)
                .rating(Rating.G)
                .releaseYear(Year.of(2022))
                .rentalDuration(rentalDuration)
                .replacementCost(BigDecimal.valueOf(99.0))
                .rentalRate(BigDecimal.valueOf(0.99))
                .specialFeatures(new HashSet<>(List.of(SpecialFeature.BEHIND_THE_SCENES, SpecialFeature.DELETED_SCENES)))
                .build();
    }

    private static Set<Actor> createActors() {
        Set<Actor> actors = new HashSet<>();

        actors.add(Actor.builder()
                .firstName("One")
                .lastName("First")
                .build());

        actors.add(Actor.builder()
                .firstName("Two")
                .lastName("Second")
                .build());

        actors.add(Actor.builder()
                .firstName("Three")
                .lastName("Third")
                .build());

        return actors;
    }

    private static Inventory createInventory(Film film, Store store) {
        return Inventory.builder()
                .film(film)
                .store(store)
                .build();
    }
}