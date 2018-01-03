package com.zhouzhuo.server.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.zhouzhuo.client.Book;
import com.zhouzhuo.client.BookManager;

import java.util.ArrayList;
import java.util.List;

public class AIDLService extends Service {
    private final String TAG = this.getClass().getSimpleName();
    //包含book对象的list
    private List<Book> mBooks = new ArrayList<>();
    @Override
    public IBinder onBind(Intent intent) {
        return manager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Book book = new Book();
        book.setName("Android 开发艺术探索");
        book.setPrice(28);
        mBooks.add(book);
        super.onCreate();
    }
    private BookManager.Stub manager = new BookManager.Stub() {
        @Override
        public List<Book> getBooks() throws RemoteException {
            Log.e("zhouzhuo","invoking getBooks() method,now the list is:"+mBooks.toString());
            if(mBooks!=null){
                return mBooks;
            }
            return new ArrayList<>();
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (this){
                if(mBooks == null){
                    mBooks = new ArrayList<>();
                }
                if(book == null){
                    book = new Book();
                    book.setPrice(22222);
                    book.setName("aaa");
                }

                if(!mBooks.contains(book)){
                    mBooks.add(book);
                }
                //打印mBooks列表,观察客户端传过来的值
                Log.e("zhouzhuo","invoking addBooks() method,now the list is:"+mBooks.toString());
            }

        }
    };


}
