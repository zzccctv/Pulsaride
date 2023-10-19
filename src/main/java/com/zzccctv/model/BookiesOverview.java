package com.zzccctv.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class BookiesOverview extends AbstractOverview {
    private List<String> bookies;

    public BookiesOverview() {
        bookies = new ArrayList<>();
    }

    public List<String> getBookies() {
        return bookies;
    }

    public void addBookies(String bookie) {
        this.bookies.add(bookie);
    }
}
