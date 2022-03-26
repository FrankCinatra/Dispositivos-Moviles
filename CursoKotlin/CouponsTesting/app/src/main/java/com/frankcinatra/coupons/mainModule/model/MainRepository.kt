package com.frankcinatra.coupons.mainModule.model

import com.frankcinatra.coupons.common.entities.CouponEntity
import com.frankcinatra.coupons.common.utils.Constants
import com.frankcinatra.coupons.common.utils.validateTextCode

class MainRepository {
    private val roomDatabase = RoomDatabase()

    suspend fun consultCouponByCode(code: String) = roomDatabase.consultCouponByCode(code)

    suspend fun saveCoupon(couponEntity: CouponEntity){
        if (validateTextCode(couponEntity.code)){
            roomDatabase.saveCoupon(couponEntity)
        } else {
            throw Exception(Constants.ERROR_LENGTH)
        }
    }
}