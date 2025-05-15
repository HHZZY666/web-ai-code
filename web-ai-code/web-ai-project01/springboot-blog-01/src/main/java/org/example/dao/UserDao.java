package org.example.dao;

import org.springframework.stereotype.Component;

import java.util.List;

public interface UserDao {

    public List<String> findAll();
}
