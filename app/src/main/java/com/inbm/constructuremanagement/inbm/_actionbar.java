package com.inbm.constructuremanagement.inbm;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class _actionbar {

    private int menu;
    private OnMenuIconClickListener onMenuIconClickListener;

    private _actionbar(Builder builder) {
        this.menu = builder.menu;
        this.onMenuIconClickListener = builder.onMenuIconClickListener;
    }

    public int getMenu() {
        return menu;
    }

    public OnMenuIconClickListener getOnMenuIconClickListener() {
        return onMenuIconClickListener;
    }

    public interface OnMenuIconClickListener {
        boolean onMenuIconClick(MenuItem item);
    }

    public static class Builder {

        private AppCompatActivity view;
        private int menu;

        private String title;
        private int backIcon;

        private OnMenuIconClickListener onMenuIconClickListener;

        public Builder(AppCompatActivity view) {
            this.view = view;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setBackIcon(int backIcon) {
            this.backIcon = backIcon;
            return this;
        }

        public Builder setMenu(int menu, OnMenuIconClickListener onMenuIconClickListener) {
            this.menu = menu;
            this.onMenuIconClickListener = onMenuIconClickListener;
            return this;
        }

        public _actionbar build() {
            ActionBar actionBar = view.getSupportActionBar();
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setElevation(0);
            if (title != null) {
                actionBar.setTitle(title);
                actionBar.setDisplayShowTitleEnabled(true);
            }
            if (backIcon > 0) {
                actionBar.setHomeAsUpIndicator(backIcon);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            return new _actionbar(this);
        }

    }

}
