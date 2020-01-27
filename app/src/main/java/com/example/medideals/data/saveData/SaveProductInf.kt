package com.example.medideals.data.saveData

import android.os.Parcel
import android.os.Parcelable

class SaveProductInf() : Parcelable
{

    var vendor_id : String = ""
    var product_id : String = ""
    var product_name : String = ""
    var product_description : String = ""
    var category : String = ""
    var image : String = ""
    var maximum_retail_Price : String = ""
    var discounted_price : String = ""
    var discount_percent : String = ""
    var quantity : String = ""
    var minquantity : String = ""
    var company_name : String = ""

    constructor(parcel: Parcel) : this() {
        vendor_id = parcel.readString()
        product_id = parcel.readString()
        product_name = parcel.readString()
        product_description = parcel.readString()
        category = parcel.readString()
        image = parcel.readString()
        maximum_retail_Price = parcel.readString()
        discounted_price = parcel.readString()
        discount_percent = parcel.readString()
        quantity = parcel.readString()
        minquantity = parcel.readString()
        company_name = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(vendor_id)
        parcel.writeString(product_id)
        parcel.writeString(product_name)
        parcel.writeString(product_description)
        parcel.writeString(category)
        parcel.writeString(image)
        parcel.writeString(maximum_retail_Price)
        parcel.writeString(discounted_price)
        parcel.writeString(discount_percent)
        parcel.writeString(quantity)
        parcel.writeString(minquantity)
        parcel.writeString(company_name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SaveProductInf> {
        override fun createFromParcel(parcel: Parcel): SaveProductInf {
            return SaveProductInf(parcel)
        }

        override fun newArray(size: Int): Array<SaveProductInf?> {
            return arrayOfNulls(size)
        }
    }


}