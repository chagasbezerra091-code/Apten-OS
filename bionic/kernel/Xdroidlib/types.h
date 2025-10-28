// Arquivo: bionic/kernel/Xdroidlib/types.h

#ifndef APTEN_OS_KERNEL_TYPES_H
#define APTEN_OS_KERNEL_TYPES_H

// --- Tipos Inteiros de Largura Fixa (do standard C99) ---
// Usamos o <stdint.h> como base, se disponivel no toolchain do Apten OS

#include <stdint.h> 

// Tipos Inteiros Sem Sinal
typedef uint8_t   u8;   // 8 bits sem sinal
typedef uint16_t  u16;  // 16 bits sem sinal
typedef uint32_t  u32;  // 32 bits sem sinal
typedef uint64_t  u64;  // 64 bits sem sinal

// Tipos Inteiros Com Sinal
typedef int8_t    s8;   // 8 bits com sinal
typedef int16_t   s16;  // 16 bits com sinal
typedef int32_t   s32;  // 32 bits com sinal
typedef int64_t   s64;  // 64 bits com sinal

// --- Tipos Comuns do Kernel ---

typedef u32       apt_paddr_t; // Tipo para Endereços Físicos de Memória
typedef u64       apt_vaddr_t; // Tipo para Endereços Virtuais de Memória
typedef u64       apt_size_t;  // Tipo para Tamanhos/Contagens de Memória
typedef s64       apt_ssize_t; // Tipo para Tamanho com Sinal (para retornos de funcoes)

// --- Tipos de Retorno ---

#define APT_OK          0      // Sucesso
#define APT_ERR_GENERIC -1     // Erro generico
#define APT_ERR_INVALID -2     // Argumento invalido
#define APT_ERR_CORE_INIT -100 // Exemplo de erro de inicializacao de modulo core

// --- Macros de Tamanho ---
#define KB_SHIFT        10
#define MB_SHIFT        20
#define GB_SHIFT        30

#define KB              (1UL << KB_SHIFT)
#define MB              (1UL << MB_SHIFT)
#define GB              (1UL << GB_SHIFT)

// --- Estruturas de Tempo ---

/**
 * @brief Estrutura basica de tempo do kernel.
 */
typedef struct {
    u64 seconds;    // Segundos desde a Epoch
    u32 nanoseconds; // Nano-segundos
} apt_time_t;

#endif // APTEN_OS_KERNEL_TYPES_H
