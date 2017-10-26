package com.mikeporet.autoremind;

import java.io.Serializable;

/**
 * Created by mikeporet on 10/18/17.
 */

public class Supply implements Serializable {

    private String title;
    private String subTitle;
    private int image;

    public Supply(String title, int image, String subTitle) {
        this.title = title;
        this.image = image;
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
