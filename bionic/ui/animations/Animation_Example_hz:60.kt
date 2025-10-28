// Apten OS Bionic: ui/animations/Animation_Example_hz:60.kt
// Copyright (c) 2025-2026 Chagas LLC. All rights reserved.
// Licença: Apache 2.0 (Componente de UI/Framework)

package com.chagasllc.apten.ui.animations

import com.chagasllc.apten.ui.animations.AnimationsManager.Duration
import com.chagasllc.apten.ui.animations.AnimationsManager.Interpolator

/**
 * # Animation 60Hz Example: Element Dismissal (Voltando à Solidão)
 *
 * Exemplo de uma animação de "dismiss" (descarte/saída) que move um painel 
 * discretamente para fora da tela, utilizando as regras de calma e foco do Apten OS.
 */
object DismissalAnimation60Hz {

    // A taxa de atualização do display (Apten OS padrão)
    private const val FRAME_RATE_HZ = 60 
    
    // Cálculo para encontrar o tempo de cada frame em milissegundos.
    private const val MS_PER_FRAME: Float = 1000f / FRAME_RATE_HZ.toFloat() 

    /**
     * Calcula o número de frames que a animação deve durar para manter a fluidez.
     * @param durationMs A duração base em milissegundos definida no AnimationsManager.
     * @return O número de frames inteiros.
     */
    fun calculateFrames(durationMs: Long): Int {
        // Arredonda para o número inteiro de frames mais próximo.
        return (durationMs.toFloat() / MS_PER_FRAME).toInt()
    }
    
    /**
     * Inicia a animação de descarte para um elemento (ex: painel de notificação).
     * O elemento é movido para baixo de forma suave, refletindo a "calma".
     */
    fun startDismissAnimation(element: AnimatableElement) {
        
        // 1. Duração: Usamos a duração 'NORMAL' e a ajustamos pela carga do sistema.
        val baseDuration = Duration.NORMAL
        val finalDuration = AnimationsManager.getOptimizedDuration(baseDuration)
        
        // 2. Cálculo do Frame Count: Garante que a duração corresponda ao 60Hz.
        val frameCount = calculateFrames(finalDuration)
        
        // 3. Interpolador: Usamos o DISAPPEAR_EASE (desaceleração) para um final suave.
        val interpolator = Interpolator.DISAPPEAR_EASE

        println("--- Apten UI Animation Started ---")
        println("Duration (MS): $finalDuration ms")
        println("Frame Count (60Hz): $frameCount frames")
        println("Interpolator: DISAPPEAR_EASE (Final Suave)")
        
        // Loop Simulado de Animação (No sistema real, seria um callback/coroutine)
        for (i in 0 until frameCount) {
            // Calcula a porcentagem de tempo que passou (0.0 a 1.0)
            val inputTime = i.toFloat() / frameCount.toFloat()
            
            // Aplica a curva de interpolação (suavização)
            val easedValue = interpolator.getInterpolation(inputTime)
            
            // Move o elemento
            element.translateY(easedValue * 200) // Ex: Move 200 pixels para baixo
            
            // Log de Calma: Mostra o progresso suave
            // println("Frame $i: Eased Value = $easedValue")
        }

        element.hide()
        println("--- Elemento movido para o estado L3 (Deep Solitude) ---")
    }
}

// Classe de Exemplo que representaria um objeto de UI que pode ser animado.
interface AnimatableElement {
    fun translateY(pixels: Float)
    fun hide()
}

// Objeto dummy para simulação:
data class UIPanel(var positionY: Float = 0f) : AnimatableElement {
    override fun translateY(pixels: Float) {
        // Simula o movimento do elemento na UI
        positionY = pixels 
    }
    override fun hide() {
        // Log para simular a saída de foco
        println("UIPanel: Escondido na posição final Y: $positionY")
    }
}

// Exemplo de como usar no AptenShell:
// val panel = UIPanel()
// DismissalAnimation60Hz.startDismissAnimation(panel)
