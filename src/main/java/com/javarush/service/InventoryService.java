package com.javarush.service;

import lombok.extern.slf4j.Slf4j;
import com.javarush.dao.InventoryDao;
import com.javarush.entity.Inventory;
import com.javarush.exception.NotFoundException;

import java.util.List;

@Slf4j
public class InventoryService {
    private final InventoryDao inventoryDao;

    public InventoryService() {
        this.inventoryDao = new InventoryDao();
    }

    public Inventory findAnyAvailableInventoryByStoreId(byte storeId) {
        List<Inventory> inventories = inventoryDao.findAnyAvailableByFilmTitleAndStoreId(storeId, 1, 1);

        if (inventories.isEmpty())
            throw new NotFoundException(String.format("no one available film not found in the store id=%s", storeId));

        Inventory available = inventories.get(0);

        log.info("available inventory id={} in the store id={} successfully found", available.getId(),
                available.getStore().getId());

        return available;
    }

    public Inventory addInventory(Inventory inventory) {
        Inventory saved = inventoryDao.save(inventory);

        log.info("new film: {}, inventory added: {}", saved.getFilm(), saved);

        return saved;
    }
}
