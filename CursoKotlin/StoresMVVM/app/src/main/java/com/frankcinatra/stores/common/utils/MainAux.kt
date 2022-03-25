package com.frankcinatra.stores.common.utils

import com.frankcinatra.stores.common.entities.StoreEntity

interface MainAux {
    fun hideFab(isVisible: Boolean = false)

    fun addStore(storeEntity: StoreEntity)
    fun updateStore(storeEntity: StoreEntity)
}