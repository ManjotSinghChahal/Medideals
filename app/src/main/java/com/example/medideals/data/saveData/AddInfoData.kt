package com.example.medideals.data.saveData

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


class AddInfoData() : Parcelable
     {
    var user_id : String = ""
    var acc_wholeseller : Boolean = false
    var acc_retailer : Boolean = false
    var acc_pcd : Boolean = false
    var acc_thirdParty : Boolean = false
    var acc_fmcg : Boolean = false
    var acc_doctor : Boolean = false

    var cat_allopathic : Boolean = false
    var cat_ayurvedic : Boolean = false
    var cat_fmcg : Boolean = false
    var cat_thirdParty : Boolean = false
    var cat_surgicalGoods : Boolean = false
    var cat_generics : Boolean = false

         constructor(parcel: Parcel) : this() {
             user_id = parcel.readString()
             acc_wholeseller = parcel.readByte() != 0.toByte()
             acc_retailer = parcel.readByte() != 0.toByte()
             acc_pcd = parcel.readByte() != 0.toByte()
             acc_thirdParty = parcel.readByte() != 0.toByte()
             acc_fmcg = parcel.readByte() != 0.toByte()
             acc_doctor = parcel.readByte() != 0.toByte()
             cat_allopathic = parcel.readByte() != 0.toByte()
             cat_ayurvedic = parcel.readByte() != 0.toByte()
             cat_fmcg = parcel.readByte() != 0.toByte()
             cat_thirdParty = parcel.readByte() != 0.toByte()
             cat_surgicalGoods = parcel.readByte() != 0.toByte()
             cat_generics = parcel.readByte() != 0.toByte()
         }

         override fun writeToParcel(parcel: Parcel, flags: Int) {
             parcel.writeString(user_id)
             parcel.writeByte(if (acc_wholeseller) 1 else 0)
             parcel.writeByte(if (acc_retailer) 1 else 0)
             parcel.writeByte(if (acc_pcd) 1 else 0)
             parcel.writeByte(if (acc_thirdParty) 1 else 0)
             parcel.writeByte(if (acc_fmcg) 1 else 0)
             parcel.writeByte(if (acc_doctor) 1 else 0)
             parcel.writeByte(if (cat_allopathic) 1 else 0)
             parcel.writeByte(if (cat_ayurvedic) 1 else 0)
             parcel.writeByte(if (cat_fmcg) 1 else 0)
             parcel.writeByte(if (cat_thirdParty) 1 else 0)
             parcel.writeByte(if (cat_surgicalGoods) 1 else 0)
             parcel.writeByte(if (cat_generics) 1 else 0)
         }

         override fun describeContents(): Int {
             return 0
         }

         companion object CREATOR : Parcelable.Creator<AddInfoData> {
             override fun createFromParcel(parcel: Parcel): AddInfoData {
                 return AddInfoData(parcel)
             }

             override fun newArray(size: Int): Array<AddInfoData?> {
                 return arrayOfNulls(size)
             }
         }


     }
