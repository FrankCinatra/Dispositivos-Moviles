package com.frankcinatra.coupons.mainModule.model

import android.database.sqlite.SQLiteConstraintException
import com.frankcinatra.coupons.CouponsApplication
import com.frankcinatra.coupons.common.dataAccess.CouponDao
import com.frankcinatra.coupons.common.entities.CouponEntity
import com.frankcinatra.coupons.common.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDatabase {
    private val dao: CouponDao by lazy { CouponsApplication.database.couponDao() }

    suspend fun consultCouponByCode(code: String) = dao.consultCouponByCode(code)

    suspend fun saveCoupon(couponEntity: CouponEntity) = withContext(Dispatchers.IO){
        try {
            dao.addCoupon(couponEntity)
        } catch (e: Exception){
            (e as? SQLiteConstraintException)?.let { throw Exception(Constants.ERROR_EXIST) }
            throw Exception(e.message ?: Constants.ERROR_UNKNOW)
        }
    }
}