// Arquivo: bionic/kernel/Xdroidlib/printk/printk.h

#ifndef APTEN_OS_KERNEL_PRINTK_H
#define APTEN_OS_KERNEL_PRINTK_H

#include <stdarg.h> // Para suporte a argumentos variaveis (variadic arguments)

// --- Niveis de Log do Kernel (Prioridade) ---
// Estes niveis sao geralmente usados para filtragem e criticidade.

#define KERN_EMERG    "<0>" // Condicao de emergencia (sistema inutilizavel)
#define KERN_ALERT    "<1>" // Acao deve ser tomada imediatamente
#define KERN_CRIT     "<2>" // Condicoes criticas (erros de hardware/software)
#define KERN_ERR      "<3>" // Condicoes de erro
#define KERN_WARNING  "<4>" // Condicoes de aviso ("WARKING")
#define KERN_NOTICE   "<5>" // Condicoes normais, mas significativas
#define KERN_INFO     "<6>" // Informacional (default para boot messages)
#define KERN_DEBUG    "<7>" // Debug (detalhado, muitas vezes desativado)


/**
 * @brief Funcao principal de log do Kernel do Apten OS.
 *
 * Imprime uma mensagem no console do kernel (ex: serial port) ou buffer de log.
 * O 'fmt' deve comecar com um dos Niveis de Log definidos acima (ex: KERN_INFO "Mensagem...").
 *
 * @param fmt A string de formato (deve incluir o nivel de prioridade).
 * @param ... Argumentos variaveis para a string de formato.
 * @return O numero de caracteres escritos (ou um codigo de erro).
 */
int apten_kernel_printk(const char *fmt, ...);


/**
 * @brief Versao vprintf para o kernel.
 * @param fmt A string de formato.
 * @param args A lista de argumentos variaveis.
 * @return O numero de caracteres escritos.
 */
int apten_kernel_vprintk(const char *fmt, va_list args);


// --- Macros para Uso Facil ---

// Macro de exemplo para o warning que voce especificou
#define APT_KLOG_WARNING(fmt, ...) \
    apten_kernel_printk(KERN_WARNING "APTen-Kernel: WARKING: " fmt "\n", ##__VA_ARGS__)

#endif // APTEN_OS_KERNEL_PRINTK_H
