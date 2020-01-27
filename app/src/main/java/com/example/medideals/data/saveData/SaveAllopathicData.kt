package com.example.medideals.data.saveData

import android.os.Parcel
import android.os.Parcelable

class SaveAllopathicData() : Parcelable
{

   var device_id : String = ""
   var cat_id : String = ""
   var minPrice : String = "10"
   var maxPrice : String = "500000"
   var search : String = ""
   var discount : String = ""
   var brand : String = ""
   var city : String = ""
   var state : String = ""
   var page_no : String = ""

   constructor(parcel: Parcel) : this() {
      device_id = parcel.readString()
      cat_id = parcel.readString()
      minPrice = parcel.readString()
      maxPrice = parcel.readString()
      search = parcel.readString()
      discount = parcel.readString()
      brand = parcel.readString()
      city = parcel.readString()
      state = parcel.readString()
      page_no = parcel.readString()
   }

   override fun writeToParcel(parcel: Parcel, flags: Int) {
      parcel.writeString(device_id)
      parcel.writeString(cat_id)
      parcel.writeString(minPrice)
      parcel.writeString(maxPrice)
      parcel.writeString(search)
      parcel.writeString(discount)
      parcel.writeString(brand)
      parcel.writeString(city)
      parcel.writeString(state)
      parcel.writeString(page_no)
   }

   override fun describeContents(): Int {
      return 0
   }

   companion object CREATOR : Parcelable.Creator<SaveAllopathicData> {
      override fun createFromParcel(parcel: Parcel): SaveAllopathicData {
         return SaveAllopathicData(parcel)
      }

      override fun newArray(size: Int): Array<SaveAllopathicData?> {
         return arrayOfNulls(size)
      }
   }
}