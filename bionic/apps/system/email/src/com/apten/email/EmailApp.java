// Arquivo: bionic/apps/system/email/src/com/apten/email/EmailApp.java

package com.apten.email;

import apten.app.Application; // Assumindo sua classe base Application
import apten.log.Log;           // Assumindo sua classe de Log

/**
 * Ponto de entrada da aplicacao de E-mail do sistema Apten OS.
 * Responsavel por inicializar recursos globais e o servico de sincronizacao.
 */
public class EmailApp extends Application {
    private static final String TAG = "AptenEmailApp";
    private static EmailManager sEmailManagerInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Inicializando a Aplicacao de E-mail do Apten OS.");
        
        // Inicializa o gerenciador de e-mail como um singleton global
        sEmailManagerInstance = new EmailManager(this);
        sEmailManagerInstance.startSyncService();
    }

    /**
     * @return A instancia singleton do EmailManager.
     */
    public static EmailManager getEmailManager() {
        if (sEmailManagerInstance == null) {
            // Em um caso de uso real, isso indicaria um erro grave de inicializacao.
            Log.e(TAG, "Tentativa de acessar EmailManager antes da inicializacao!");
        }
        return sEmailManagerInstance;
    }

    @Override
    public void onTerminate() {
        if (sEmailManagerInstance != null) {
            sEmailManagerInstance.stopSyncService();
        }
        Log.i(TAG, "Aplicacao de E-mail encerrada.");
        super.onTerminate();
    }
}
