package com.zendesk.demo.repo;

import com.zendesk.demo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item,Long> {

    List<Item> findAllByUserId(long userId);
}
