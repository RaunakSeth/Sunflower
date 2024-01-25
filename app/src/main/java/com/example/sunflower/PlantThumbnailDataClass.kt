package com.example.sunflower

import android.os.Parcel
import android.os.Parcelable

data class PlantThumbnailDataClass(val title:String,val imageUrl:String,val plant_id:Int,val plant_water:String):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(imageUrl)
        parcel.writeInt(plant_id)
        parcel.writeString(plant_water)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlantThumbnailDataClass> {
        override fun createFromParcel(parcel: Parcel): PlantThumbnailDataClass {
            return PlantThumbnailDataClass(parcel)
        }

        override fun newArray(size: Int): Array<PlantThumbnailDataClass?> {
            return arrayOfNulls(size)
        }
    }
}