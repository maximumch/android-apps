package com.example.student1.apps;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// Адаптер возвращает View, состоящий из ImageView и TextView
// Создает их по ApplicationInfo
public class ApplicationAdapter extends ArrayAdapter {

    // Контейнер ApplicationInfo
    private List<ApplicationInfo> info;
    private LayoutInflater inflater;
    private PackageManager manager;

    public ApplicationAdapter(Context context, int resource, List<ApplicationInfo> info) {
        super(context, resource);
        this.info = info;
        inflater = LayoutInflater.from(context);
        manager = context.getPackageManager();
    }

    // Для экономии времени - 
    // стоило бы использовать шаблон ViewHolder, чтобы
    // не создавать View лишний раз
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Элемент контейнера, соответствующий информации о приложении
        ApplicationInfo i = info.get(position);

        // Создадим View
        //
        //
        if(convertView==null){
            convertView = inflater.inflate(R.layout.item,null);
        }


        //View view = null;

        // Найдем ссылки на нужные виджеты по их идентификаторам
        //
        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        TextView name = (TextView) convertView.findViewById(R.id.name);



        // Название приложения
        name.setText(i.loadLabel(manager).toString());
        //
        //


        // Иконка приложения
         Drawable appIcon = manager.getApplicationIcon(i);
        // Присвоим ее картинке
        image.setImageDrawable(appIcon);

        // Возвратим 
        return convertView;
    }

    // ListView должен знать, сколько всего элементов
    // в адаптере
    @Override
    public int getCount() {
        return info.size();
    }


}
