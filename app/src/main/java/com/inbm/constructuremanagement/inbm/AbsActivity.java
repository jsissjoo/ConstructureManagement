package com.inbm.constructuremanagement.inbm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public abstract class AbsActivity extends AppCompatActivity {

    protected _actionbar actionbar;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (actionbar != null && actionbar.getMenu() !=0) {
            getMenuInflater().inflate(actionbar.getMenu(), menu);
            return true;
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionbar != null && actionbar.getOnMenuIconClickListener() != null) {
            return actionbar.getOnMenuIconClickListener().onMenuIconClick(item);
        }

        return super.onOptionsItemSelected(item);
    }

    protected abstract int getLayout();
    protected abstract void buildUI();
    protected abstract _actionbar getCustomActionBar();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionbar = getCustomActionBar();
        if (actionbar == null) {
            getSupportActionBar().hide();
        }

        setContentView(getLayout());
        buildUI();
    }
}
