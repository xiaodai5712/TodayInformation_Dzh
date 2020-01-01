package com.dzh.http.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.dzh.http.annotation.RequestMethod.Get;
import static com.dzh.http.annotation.RequestMethod.Post;

@IntDef({Get,Post})
@Retention(RetentionPolicy.SOURCE)
public @interface RequestMethod
{
    int Get = 1;
    int Post = 2;
}
