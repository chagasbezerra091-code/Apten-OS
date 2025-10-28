// Arquivo: bionic/apps/system/settings/src/com/apten/settings/SettingsActivity.java

package com.apten.settings;

import apten.app.Activity;
import apten.ui.ListView;
import apten.view.View;
import apten.content.Intent;
import apten.log.Log;
import com.apten.settings.network.NetworkSettingsActivity; // A ser criado

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Activity Principal de Configuracoes.
 * Exibe a lista de categorias de configuracoes do sistema.
 */
public class SettingsActivity extends Activity {
    private static final String TAG = "SettingsUI";
    private ListView mCategoryListView;
    
    private final List<Map<String, Object>> mCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // 1. Configuracao da View (simulacao)
        setContentView(R.layout.activity_main_settings); 
        mCategoryListView = findViewById(R.id.settings_category_list);

        // 2. Define os dados (Categorias)
        setupCategories();

        // 3. Define o Listener para clique
        mCategoryListView.setOnItemClickListener((parent, view, position, id) -> {
            Map<String, Object> category = mCategories.get(position);
            navigateToCategory((Class<? extends Activity>)category.get("targetActivity"));
        });
        
        // 4. Atribui o Adapter (Simulacao)
        // mCategoryListView.setAdapter(new SettingsAdapter(this, mCategories));
    }
    
    /**
     * Define as categorias de configuracao e suas Activities de destino.
     */
    private void setupCategories() {
        // Categoria 1: Rede & Internet
        Map<String, Object> network = new HashMap<>();
        network.put("title", "Rede e Internet");
        network.put("icon_res", R.drawable.ic_settings_wifi);
        network.put("targetActivity", NetworkSettingsActivity.class);
        mCategories.add(network);

        // Categoria 2: Display (Tela)
        Map<String, Object> display = new HashMap<>();
        display.put("title", "Tela & Brilho");
        display.put("icon_res", R.drawable.ic_settings_display);
        display.put("targetActivity", DisplaySettingsActivity.class); // A ser criado
        mCategories.add(display);
        
        // Categoria 3: Xdroidlib (Configuracoes de Kernel/Desenvolvedor)
        Map<String, Object> kernel = new HashMap<>();
        kernel.put("title", "Opcoes do Xdroidlib (Kernel)");
        kernel.put("icon_res", R.drawable.ic_settings_dev);
        kernel.put("targetActivity", XdroidDevActivity.class); // A ser criado
        mCategories.add(kernel);

        Log.i(TAG, mCategories.size() + " categorias de configuracao definidas.");
    }
    
    /**
     * Navega para a Activity de detalhe da categoria.
     */
    private void navigateToCategory(Class<? extends Activity> targetClass) {
        if (targetClass != null) {
            Intent intent = new Intent(this, targetClass);
            startActivity(intent);
        } else {
            Log.e(TAG, "Tentativa de navegar para uma categoria sem Activity definida.");
            showToast("A configuracao ainda nao esta implementada.");
        }
    }
    
    // Placeholder para exibir Toast
    private void showToast(String message) {
        Log.i(TAG, "TOAST: " + message);
        // Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
