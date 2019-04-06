package com.example.tasklisttodo

import android.os.Parcel
import android.os.Parcelable

class SimpleTask (description: String, importance: String, url: Int, time: String) : Parcelable{
    var Description = description
        get() = field
        set(value) {
            field = value
        }
    var Importance = importance
        get() = field
        set(value) {
            field = value
        }
    var ImgURL=url
        get() = field
        set(value) {
            field = value
        }
    var Time = time
        get() = field
        set(value) {
            field = value
        }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Description)
        parcel.writeString(Importance)
        parcel.writeInt(ImgURL)
        parcel.writeString(Time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SimpleTask> {
        override fun createFromParcel(parcel: Parcel): SimpleTask {
            return SimpleTask(parcel)
        }

        override fun newArray(size: Int): Array<SimpleTask?> {
            return arrayOfNulls(size)
        }
    }
}