// Arquivo: bionic/apps/system/settings/src/com/apten/settings/SettingsApp.java

package com.apten.settings;

import apten.app.Application;
import apten.log.Log;

/**
 * Ponto de entrada da Aplicacao de Configuracoes do sistema Apten OS.
 * Responsavel por inicializar o Gerenciador de Configuracoes global.
 */
public class SettingsApp extends Application {
    private static final String TAG = "AptenSettingsApp";
    private static SettingManager sManagerInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Inicializando a Aplicacao de Configuracoes do Apten OS.");
        
        // Inicializa o gerenciador de configuracoes como um singleton
        sManagerInstance = new SettingManager(this);
    }

    /**
     * @return A instancia singleton do SettingManager, usado por todas as telas.
     */
    public static SettingManager getManager() {
        if (sManagerInstance == null) {
            Log.e(TAG, "Tentativa de acessar SettingManager antes da inicializacao!");
        }
        return sManagerInstance;
    }

    @Override
    public void onTerminate() {
        Log.i(TAG, "Aplicacao de Configuracoes encerrada.");
        super.onTerminate();
    }
}
