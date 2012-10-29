package models;

import util.exceptions.ConnectionException;

/**
 * Author: nanne
 */
public interface Item {
    void remove() throws ConnectionException;
    void save() throws ConnectionException;
}
