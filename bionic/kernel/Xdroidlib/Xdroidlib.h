// Arquivo: bionic/kernel/Xdroidlib/Xdroidlib.h

#ifndef APTEN_OS_XDROIDLIB_H
#define APTEN_OS_XDROIDLIB_H

// --- Inclusão de Headers Essenciais ---
#include "types.h"          // Tipos basicos do Apten OS
#include "printk/printk.h"  // Subsistema de Logging do Kernel
// #include "code_error/error.h" // A ser criado para uso de codigos de erro

// --- Informações e Versão da Biblioteca ---
#define XDROIDLIB_MAJOR_VERSION 1
#define XDROIDLIB_MINOR_VERSION 0
#define XDROIDLIB_VERSION_STRING "Apten-Kernel-Xdroidlib-v1.0"


// --- Definições de Flags Comuns ---

/**
 * @brief Flags de uso geral para operacoes de kernel.
 */
typedef enum {
    APTEN_FLAG_READ         = (1 << 0),  // Permissao de leitura
    APTEN_FLAG_WRITE        = (1 << 1),  // Permissao de escrita
    APTEN_FLAG_EXCLUSIVE    = (1 << 2),  // Acesso exclusivo
    APTEN_FLAG_NONBLOCK     = (1 << 3)   // Operacao nao bloqueante
} XdroidFlags;


// --- Funções de Inicialização e Core ---

/**
 * @brief Inicializa todos os subsistemas da Xdroidlib.
 *
 * Deve ser chamada muito cedo na fase de boot do kernel.
 *
 * @param boot_flags Flags de inicializacao passadas pelo bootloader.
 * @return APT_OK em sucesso, ou um codigo de erro (ex: APT_ERR_CORE_INIT).
 */
int apten_xdroidlib_init(u32 boot_flags);

/**
 * @brief Funcao de Panico do Kernel do Apten OS.
 *
 * Interrompe a execucao, desabilita interrupcoes e imprime uma mensagem critica.
 *
 * @param message A mensagem de erro a ser exibida.
 */
void apten_kernel_panic(const char* message);

#endif // APTEN_OS_XDROIDLIB_H
