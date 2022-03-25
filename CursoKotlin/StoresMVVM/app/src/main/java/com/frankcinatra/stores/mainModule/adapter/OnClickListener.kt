package com.frankcinatra.stores.mainModule.adapter

import com.frankcinatra.stores.common.entities.StoreEntity

interface OnClickListener {
    fun onClick(storeId: StoreEntity)
    fun onFavoriteStore(storeEntity: StoreEntity)
    fun onDeleteStore(storeEntity: StoreEntity)
}