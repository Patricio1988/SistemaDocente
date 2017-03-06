package com.ec.uce.medicina.docente.alfresco.modelo;
import com.google.api.client.util.Key;

/**
 * @author jpotts
 */
public class Pagination {
    @Key
    public int count;

    @Key
    public boolean hasMoreItems;

    @Key
    public int totalItems;

    @Key
    public int skipCount;

    @Key
    public int maxItems;
}