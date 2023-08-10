package com.apolisb42.newsproject.model

import android.os.Parcel
import android.os.Parcelable

data class New(
    val author: String,
    val category: List<String>,
    val description: String,
    val id: String,
    val image: String,
    val published:String,
    val url:String,
    val title: String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.createStringArrayList()?: arrayListOf<String>(),
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:""
    )

    override fun describeContents()=0

    override fun writeToParcel(dest: Parcel, flags: Int) {
    }

    companion object CREATOR : Parcelable.Creator<New> {
        override fun createFromParcel(parcel: Parcel): New {
            return New(parcel)
        }

        override fun newArray(size: Int): Array<New?> {
            return arrayOfNulls(size)
        }
    }
}