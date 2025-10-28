// Arquivo: bionic/apps/system/ocults/teste_de_automação/src/com/apten/ocults/autotests/KernelIntegrationTest.java

package com.apten.ocults.autotests;

import apten.log.Log;

/**
 * Classe utilitaria JNI para rodar testes de integridade de kernel de baixo nivel.
 * Depende da biblioteca nativa (Xdroidlib).
 */
public class KernelIntegrationTest {
    private static final String TAG = "KernelJNI";
    
    // Carrega a biblioteca nativa Xdroidlib (assumindo que esta no caminho de busca do sistema)
    static {
        try {
            System.loadLibrary("xdroidlib"); 
        } catch (UnsatisfiedLinkError e) {
            Log.e(TAG, "Falha ao carregar a biblioteca nativa 'xdroidlib': " + e.getMessage());
        }
    }

    // Funcao nativa que chama o código C da Xdroidlib para rodar um teste.
    public static native int runIntegrityCheck();
    
    // Funcao nativa que expoe o printk de emergencia (KERN_EMERG)
    public static native void nativeTriggerKernelPanic(String message);
    
    // Funcao de wrapper para uso facil no Java
    public static void runTest() {
        Log.d(TAG, "Chamando teste de integridade via JNI...");
        int result = runIntegrityCheck();
        
        if (result == 0) { // APT_OK
            Log.i(TAG, "Verificacao de integridade do Kernel: OK.");
        } else {
            Log.e(TAG, "Falha na Verificacao de Integridade do Kernel. Codigo: " + result);
            // Chama o panic do Kernel se o teste for critico
            if (result == -100) {
                 nativeTriggerKernelPanic("Teste de Integridade Critico JNI Falhou");
            }
        }
    }
}
