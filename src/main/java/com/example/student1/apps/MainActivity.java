package com.example.student1.apps;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


// Дополнительная информация:
// http://stackoverflow.com/questions/5385957/how-to-get-apps-permission-for-each-app-how-to-do-it-programmatically-on-andro

public class MainActivity extends AppCompatActivity {

    // Ключ, по которому передаем через Intent
    // массив разрешений
    public static final String PERMISSIONS = "PERMISSIONS";

    private ListView list;

    // По одному элементу ApplicationInfo на каждое
    // установленное приложение
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.apps_list);

        // Через PackageManager
        PackageManager pm = getPackageManager();
        // Получим информацию обо всех приложениях
        // установленных на устройстве
        //List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        final List<ApplicationInfo> apps = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        /*
        for (ApplicationInfo applicationInfo : packages) {
            apps.add(applicationInfo);
        }
        */

        // Нестандартный адаптер делающий  View
        // из картинки и текстового поля
        ApplicationAdapter adapter = new ApplicationAdapter(this, R.layout.item, apps);
        list.setAdapter(adapter);

        // По клику на элемент ListView
        // получаеи список разрешений у приложения
        // создаем Intent для запуска Activity 
        // и добавляем туда массив разрешений по ключу
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ApplicationInfo info = apps.get(position);

                // Intent
                Intent intent = new Intent(MainActivity.this, Permissions.class);
                try {
                    PackageInfo packageInfo = MainActivity.this.getPackageManager().getPackageInfo(info.packageName, PackageManager.GET_PERMISSIONS);
                    // Массив разрешений
                    String[] requestedPermissions = packageInfo.requestedPermissions;
                    // Положим в Intent
                    intent.putExtra(PERMISSIONS, requestedPermissions);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                // Запустим
                startActivity(intent);
            }
        });

    }
}
