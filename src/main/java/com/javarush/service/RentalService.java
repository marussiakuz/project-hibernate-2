package com.javarush.service;

import lombok.extern.slf4j.Slf4j;
import com.javarush.dao.RentalDao;
import com.javarush.entity.Rental;
import com.javarush.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class RentalService {
    private final RentalDao rentalDao;

    public RentalService() {
        this.rentalDao = new RentalDao();
    }

    public Rental getAnyRentalNotReturnedByStoreId(byte storeId) {
        List<Rental> rentals = rentalDao.getAllRentalNotReturned(storeId, 1, 1);

        if (rentals.isEmpty())
            throw new NotFoundException(String.format("no one rental that not returned else found in the store id=%s",
                    storeId));

        Rental notReturned = rentals.get(0);

        log.info("rental that hasn't yet been returned was found: {}", notReturned);

        return notReturned;
    }

    public Rental returnFilm(short customerId, short filmId, byte storeId) {
        Rental rental = rentalDao.findRentalByCustomerIdAndRentalIdAndStoreId(customerId, filmId, storeId)
                .orElseThrow(() -> new NotFoundException(String.format("customer id=%s hasn't rented film id=%s in " +
                        "the store id=%s", customerId, filmId, storeId)));

        rental.setReturnDate(LocalDateTime.now());

        Rental updated = rentalDao.update(rental);

        log.info("rental has been returned: {}", updated);

        return updated;
    }
}
