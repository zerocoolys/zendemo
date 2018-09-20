package com.zendesk.demo.service;

import com.zendesk.demo.model.Item;
import com.zendesk.demo.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;

    public Item createItem(long userId, Item item) {
        return itemRepo.save(item.setUserId(userId));
    }

    public boolean exists(long itemId) {
        return itemRepo.findById(itemId).isPresent();
    }

    public Item findItemById(long itemId) {
        return itemRepo.findById(itemId).orElse(null);
    }

    public void reprice(Item item, double price) {
        itemRepo.save(item.setPrice(price));
    }

    public List<Item> findItemByUserId(long userId) {
        return itemRepo.findAllByUserId(userId);
    }
}
