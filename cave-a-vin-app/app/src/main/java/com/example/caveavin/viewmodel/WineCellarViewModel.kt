package com.example.caveavin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.caveavin.data.WineCellarRepository
import com.example.caveavin.data.model.Bottle
import com.example.caveavin.data.model.Tasting
import com.example.caveavin.data.model.WineType
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WineCellarViewModel(
    private val repository: WineCellarRepository
) : ViewModel() {
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    private val _selectedType = MutableStateFlow<WineType?>(null)
    val selectedType: StateFlow<WineType?> = _selectedType.asStateFlow()
    
    val bottles: StateFlow<List<Bottle>> = combine(
        repository.getAllBottles(),
        _searchQuery,
        _selectedType
    ) { bottles, query, type ->
        bottles.filter { bottle ->
            val matchesSearch = query.isEmpty() || 
                bottle.name.contains(query, ignoreCase = true) ||
                bottle.appellation.contains(query, ignoreCase = true) ||
                bottle.region.contains(query, ignoreCase = true)
            
            val matchesType = type == null || bottle.type == type
            
            matchesSearch && matchesType
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
    
    val totalBottles: StateFlow<Int> = repository.getTotalBottles()
        .map { it ?: 0 }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )
    
    val totalValue: StateFlow<Double> = repository.getTotalValue()
        .map { it ?: 0.0 }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )
    
    val regions: StateFlow<List<String>> = repository.getAllRegions()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }
    
    fun setSelectedType(type: WineType?) {
        _selectedType.value = type
    }
    
    fun insertBottle(bottle: Bottle) {
        viewModelScope.launch {
            repository.insertBottle(bottle)
        }
    }
    
    fun updateBottle(bottle: Bottle) {
        viewModelScope.launch {
            repository.updateBottle(bottle)
        }
    }
    
    fun deleteBottle(bottle: Bottle) {
        viewModelScope.launch {
            repository.deleteBottle(bottle)
        }
    }
    
    fun updateQuantity(bottleId: Long, newQuantity: Int) {
        viewModelScope.launch {
            repository.updateQuantity(bottleId, newQuantity)
        }
    }
    
    fun getTastingsForBottle(bottleId: Long): Flow<List<Tasting>> {
        return repository.getTastingsForBottle(bottleId)
    }
    
    fun insertTasting(tasting: Tasting) {
        viewModelScope.launch {
            repository.insertTasting(tasting)
        }
    }
    
    suspend fun getBottleById(id: Long): Bottle? {
        return repository.getBottleById(id)
    }
}

class WineCellarViewModelFactory(
    private val repository: WineCellarRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WineCellarViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WineCellarViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
