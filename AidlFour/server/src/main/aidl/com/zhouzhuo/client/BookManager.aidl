// BookManager.aidl
package com.zhouzhuo.client;

// Declare any non-default types here with import statements
import com.zhouzhuo.client.Book;

interface BookManager {
    List<Book> getBooks();
        void addBook(inout Book book);
}
