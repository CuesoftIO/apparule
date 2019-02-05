package io.cuesoft.apparule.views.designer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import io.cuesoft.apparule.R;

public class EditCatalogueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_catalogue);

        Toolbar toolbar = findViewById(R.id.edit_CatalogueToolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditCatalogueActivity.this, CatalogueActivity.class);
                startActivity(intent);
            }
        });
    }
}
