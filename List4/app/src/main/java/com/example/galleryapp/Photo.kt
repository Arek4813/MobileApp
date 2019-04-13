package com.example.galleryapp

import android.os.Parcel
import android.os.Parcelable

class Photo (description: String, url: Int, rate: Double, index:Int) : Parcelable {

    var Description = description
        get() = field
        set(value) {
            field = value
        }
    var Rating = rate
        get() = field
        set(value) {
            field = value
        }
    var PhotoURL=url
        get() = field
        set(value) {
            field = value
        }
    var Index = index
        get() = field
        set(value) {
            field = value
        }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Description)
        parcel.writeInt(PhotoURL)
        parcel.writeDouble(Rating)
        parcel.writeInt(Index)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }
}