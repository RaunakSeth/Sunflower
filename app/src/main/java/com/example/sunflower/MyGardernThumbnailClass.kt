package com.example.sunflower

import android.os.Parcel
import android.os.Parcelable

data class MyGardernThumbnailClass(val title:String,val imageUrl:String,val planted:String,var plant_water:String):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(imageUrl)
        parcel.writeString(planted)
        parcel.writeString(plant_water)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyGardernThumbnailClass> {
        override fun createFromParcel(parcel: Parcel): MyGardernThumbnailClass {
            return MyGardernThumbnailClass(parcel)
        }

        override fun newArray(size: Int): Array<MyGardernThumbnailClass?> {
            return arrayOfNulls(size)
        }
    }
}
