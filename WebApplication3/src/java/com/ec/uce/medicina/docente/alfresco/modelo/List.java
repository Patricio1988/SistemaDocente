package com.ec.uce.medicina.docente.alfresco.modelo;

import java.util.ArrayList;

import com.google.api.client.util.Key;

/**
 * @author jpotts
 */
public class List<T extends Entry> {
    @Key
    public ArrayList<T> entries;

    @Key
    public Pagination pagination;
}
