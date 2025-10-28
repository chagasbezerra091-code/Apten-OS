# Apten OS Bionic: ui/test_file_no_delete/test.py
# Copyright (c) 2025-2026 Chagas LLC. All rights reserved.
# Licença: Apache 2.0 (Componente de Teste de UI)

"""
TESTE CRÍTICO DE CALMA (STILLNESS TEST)

Verifica se o AnimationsManager ajusta corretamente a duração das animações
baseado na carga do sistema, priorizando a responsividade (Foco) sobre a suavidade
quando o AptenFocus Scheduler está sob estresse.
"""

# --- Constantes do Apten OS (Simulação do AnimationsManager.kt) ---
DURATION_FAST_MS = 100
DURATION_NORMAL_MS = 200
LOAD_THRESHOLD = 0.7  # Limite para considerar o sistema sob "Heavy Load"

def simulate_get_load_status(load_level: float) -> float:
    """
    Simula a função do AptenFocusScheduler que reporta a carga do sistema.
    """
    return load_level

def get_optimized_duration(base_duration: int, current_load: float) -> int:
    """
    Simula a lógica do AnimationsManager.getOptimizedDuration.
    """
    is_heavy_load = current_load > LOAD_THRESHOLD
    
    if is_heavy_load:
        # Se sob carga, forçar a animação mais rápida para manter o Foco.
        return DURATION_FAST_MS
    else:
        # Se o sistema está Calmo, usar a duração base (suave).
        return base_duration

# --- Execução dos Casos de Teste ---

def run_stillness_tests():
    print("--- Testes de Calma (Stillness Test) do Apten OS ---")
    
    # Caso 1: Sistema em Calma (Ocioso)
    load_calm = simulate_get_load_status(0.2)
    duration_calm = get_optimized_duration(DURATION_NORMAL_MS, load_calm)
    
    print(f"\nCaso 1: Carga do Sistema: {load_calm:.2f} (CALMA)")
    print(f"Duração Esperada: {DURATION_NORMAL_MS}ms")
    print(f"Duração Real: {duration_calm}ms")
    
    assert duration_calm == DURATION_NORMAL_MS, "Falha no Caso 1: Não usou a duração suave quando Calmo."
    print("[PASS] Caso 1: A duração suave foi usada com sucesso.")


    # Caso 2: Sistema sob Carga (Quebrando a Calma)
    load_heavy = simulate_get_load_status(0.85)
    duration_heavy = get_optimized_duration(DURATION_NORMAL_MS, load_heavy)
    
    print(f"\nCaso 2: Carga do Sistema: {load_heavy:.2f} (CARGA PESADA)")
    print(f"Duração Esperada: {DURATION_FAST_MS}ms")
    print(f"Duração Real: {duration_heavy}ms")

    assert duration_heavy == DURATION_FAST_MS, "Falha no Caso 2: Não forçou a animação FAST sob carga."
    print("[PASS] Caso 2: A duração FAST foi forçada com sucesso para manter o Foco.")


    # Caso 3: Limite Exato (Deve priorizar a Calma ou o Foco?)
    load_limit = simulate_get_load_status(LOAD_THRESHOLD)
    duration_limit = get_optimized_duration(DURATION_NORMAL_MS, load_limit)
    
    # Assumimos que no limite ou abaixo, a Calma (duração normal) é priorizada.
    print(f"\nCaso 3: Carga no Limite: {load_limit:.2f} (LIMITE)")
    print(f"Duração Esperada: {DURATION_NORMAL_MS}ms")
    print(f"Duração Real: {duration_limit}ms")

    assert duration_limit == DURATION_NORMAL_MS, "Falha no Caso 3: Não priorizou a Calma no limite."
    print("[PASS] Caso 3: Prioridade de Calma mantida no limite.")

    print("\n--- Todos os Stillness Tests foram concluídos com sucesso. ---")


if __name__ == "__main__":
    run_stillness_tests()
