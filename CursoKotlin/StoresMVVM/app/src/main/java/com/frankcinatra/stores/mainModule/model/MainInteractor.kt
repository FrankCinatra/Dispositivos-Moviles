package com.frankcinatra.stores.mainModule.model

import com.frankcinatra.stores.StoreApplication
import com.frankcinatra.stores.common.entities.StoreEntity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainInteractor {

    fun getStores(callback: (MutableList<StoreEntity>) -> Unit){
        doAsync {
            val storeList = StoreApplication.database.storeDao().getAllStores()
            uiThread {
                callback(storeList)
            }
        }
    }

    fun deleteStore(storeEntity: StoreEntity, callback: (StoreEntity) -> Unit){
        doAsync {
            StoreApplication.database.storeDao().deleteStore(storeEntity)
            uiThread {
                callback(storeEntity)
            }
        }
    }

    fun updateStore(storeEntity: StoreEntity, callback: (StoreEntity) -> Unit){
        doAsync {
            StoreApplication.database.storeDao().updateStore(storeEntity)
            uiThread {
                callback(storeEntity)
            }
        }
    }
}