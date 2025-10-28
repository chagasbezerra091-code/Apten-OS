// Arquivo: bionic/vendor/vendor.cc

#include <stdio.h> // Para a funcao printf padrao

// Inclui o header de logging que voce acabou de definir
#include "aptos/vendor_log.h" 

// Etiqueta (TAG) para o modulo Vendor
#define VENDOR_TAG "APTen_VENDOR"

// Funcao de inicializacao do modulo Vendor
// Esta funcao deve ser chamada pelo seu sistema (kernel ou init process)
// na fase inicial de boot.
extern "C" void vendor_initialization_stage() {
    
    // --- Log de Emergencia (WARKING) ---
    
    // Usando a funcao customizada apten_log_type com o tipo WARNING
    apten_log_print(APT_LOG_WARNING, VENDOR_TAG, 
                    "WARKING: com a emergencia. Ativando protocolo de seguranca.");

    // Usando printf para saida rapida, se o sistema ja suportar
    printf("[%s] [WARNING] Verificacao de integridade necessaria!\n", VENDOR_TAG);

    
    // --- Informacoes do OEM (OEM) ---

    // Usando a funcao customizada apten_log_type com o tipo INFO
    apten_log_print(APT_LOG_INFO, VENDOR_TAG, 
                    "OEM: com Apten OS factory inc. Kernel Build ID: 20251028_A01");

    // --- Outras Informacoes ---

    // Informacao adicional sobre a configuracao do vendor
    apten_log_print(APT_LOG_INFO, VENDOR_TAG, 
                    "Configuracao do Vendor Carregada: Modo 'Factory' ativado.");
                    
    // Exemplo de uma variavel interna sendo impressa
    int vendor_id = 42;
    printf("[%s] ID do Dispositivo Vendor: %d\n", VENDOR_TAG, vendor_id);
}

