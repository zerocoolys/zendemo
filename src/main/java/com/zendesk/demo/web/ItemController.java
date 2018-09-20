package com.zendesk.demo.web;

import com.zendesk.demo.commons.WebResponse;
import com.zendesk.demo.model.Item;
import com.zendesk.demo.service.ItemService;
import com.zendesk.demo.service.PriceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ItemController implements BaseController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private PriceLogService priceLogService;

    @PostMapping("/items")
    public WebResponse createItem(@RequestBody Item item, HttpServletRequest request) {
        if (item == null) {
            return err(-1);
        }
        Object userId = request.getSession().getAttribute("userId");

        if (userId == null) {
            return err(-1, "session error");
        }

        item = itemService.createItem(Long.parseLong(userId.toString()), item);

        return ok(item);
    }

    @GetMapping("/items")
    public WebResponse getItemByUserId(HttpServletRequest request) {

        Object userId = request.getSession().getAttribute("userId");

        if (userId == null) {
            return err(-1, "session error");
        }

        return ok(itemService.findItemByUserId(Long.parseLong(userId.toString())));
    }

    @GetMapping("/items/{itemId}")
    public WebResponse getItemById(@PathVariable long itemId) {
        return ok(itemService.findItemById(itemId));
    }

    @PostMapping("/items/{itemId}/prices")
    public WebResponse reprice(
            @PathVariable long itemId,
            @RequestParam double price) {
        if (price <= 0) {
            return err(-1, "price error");
        }

        Item item = itemService.findItemById(itemId);
        if (item == null) {
            return err(-1, "item not exist");
        }

        itemService.reprice(item, price);
        return ok();
    }


}
