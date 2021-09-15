package com.phunguyen3107.springpetclinic.formatter;

import java.util.Locale;

public interface Formatter<T> {
    public String print(T object, Locale locale);

    public T parse(String text, Locale locale);
}
