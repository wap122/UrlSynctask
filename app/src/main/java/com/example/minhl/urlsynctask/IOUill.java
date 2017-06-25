package com.example.minhl.urlsynctask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Created by minhl on 25/06/2017.
 */

class IOUill {
    public static void closeQuiety(InputStream in) {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void closeQuiety(Reader reader) {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
