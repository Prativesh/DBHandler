package com.example.externalapiwithretrofit

import retrofit2.http.Url

class UserModel(albumId:Int, id:Int, title:String , url:Url, thumbnailUrl:Url ) {
    var albumId :Int
        get():Int {return albumId}
        set(value) {albumId=value}

    var id :Int
        get():Int {return id}
        set(value) {id=value}

    var url :Url
     get():Url {return url}
     set(value) {this.url = value}

    var thumbnailUrl :Url
        get():Url {return thumbnailUrl}
        set(value) {thumbnailUrl=value}


    var title :String

    init {
        this.albumId = albumId
        this.id = id
        this.title=title
        this.url=url
        this.thumbnailUrl=thumbnailUrl
    }
}